package yang.yu.tmall.domain.catalogue;

import yang.yu.tmall.domain.commons.BaseEntity;
import yang.yu.lang.IoC;
import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.pricing.Pricing;
import yang.yu.tmall.domain.pricing.PricingException;
import yang.yu.tmall.domain.pricing.PricingService;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;

    @ManyToOne
    private ProductCategory category;

    public Product() {
    }

    public Product(String name, ProductCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    /**
     * 设定价格，在指定时间生效
     * @param unitPrice 要设置的单价
     * @param effectiveTime 生效时间
     * @return 一个新的定价对象
     */
    public Pricing setPrice(Money unitPrice, LocalDateTime effectiveTime) {
        return getPricingService().setPrice(this, unitPrice, effectiveTime);
    }

    /**
     * 按百分比调整价格，在指定时间生效
     * @param percentage 要调整的单价的百分比。例如10就是价格上调10%，-5就是下调5%
     * @param effectiveTime 生效时间
     * @return 一个新的定价对象
     */
    public Pricing adjustPriceByPercentage(int percentage, LocalDateTime effectiveTime) {
        return getPricingService().adjustPriceByPercentage(this, percentage, effectiveTime);
    }

    /**
     * 获取特定时刻的价格
     * @param time 要查询的时刻
     * @return 指定商品在指定时刻的单价
     * @throws PricingException 当商品还没设定单价时抛出此异常
     */
    public Money priceAt(LocalDateTime time) {
        return getPricingService().priceAt(this, time);
    }

    /**
     * 获取当前价格
     * @return 商品的当前价格
     */
    public Money currentPrice() {
        return priceAt(LocalDateTime.now());
    }

    private PricingService getPricingService() {
        return IoC.getInstance(PricingService.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(getName(), product.getName()) &&
                Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCategory());
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}
