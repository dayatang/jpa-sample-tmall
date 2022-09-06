package yang.yu.tmall.domain.pricing;

import yang.yu.tmall.domain.commons.BaseEntity;
import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.catalog.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pricings")
public class Pricing extends BaseEntity {

    @ManyToOne
    private Product product;  //商品

    private Money unitPrice; //单价

    @Column(name = "effective_time")
    private LocalDateTime effectiveTime; //生效时间

    public Pricing() {
    }

    public Pricing(Product product, Money unitPrice, LocalDateTime effectiveTime) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.effectiveTime = effectiveTime;
    }

    public Pricing(Product product, Money unitPrice) {
        this(product, unitPrice, LocalDateTime.now());
    }

    public Product getProduct() {
        return product;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public LocalDateTime getEffectiveTime() {
        return effectiveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pricing)) {
            return false;
        }
        Pricing pricing = (Pricing) o;
        return getProduct().equals(pricing.getProduct()) &&
                getEffectiveTime().equals(pricing.getEffectiveTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getEffectiveTime());
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "product=" + product +
                ", unitPrice=" + unitPrice +
                ", pricingTime=" + effectiveTime +
                '}';
    }
}
