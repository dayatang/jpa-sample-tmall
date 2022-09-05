package yang.yu.tmall.domain.pricing;

import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.catalogue.Product;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 定价领域服务
 */
@Named
public class PricingService {

    private Pricings pricings;

    /**
     * 构造函数
     * @param pricings 定价仓储
     */
    public PricingService(Pricings pricings) {
        this.pricings = pricings;
    }

    /**
     * 为单个产品设定价格，在指定时间生效
     * @param product 要设定价格的商品
     * @param unitPrice 要设置的单价
     * @param effectiveTime 生效时间
     * @return 一个新的定价对象
     */
    public Pricing setPrice(Product product, Money unitPrice, LocalDateTime effectiveTime) {
        return pricings.save(new Pricing(product, unitPrice, effectiveTime));
    }

    /**
     * 按百分比对单个产品调整价格，在指定时间生效
     * @param product 要设定价格的商品
     * @param percentage 要调整的单价的百分比。例如10就是价格上调10%，-5就是下调5%
     * @param effectiveTime 生效时间
     * @return 一个新的定价对象
     */
    public Pricing adjustPriceByPercentage(Product product, int percentage, LocalDateTime effectiveTime) {
        Money newPrice = currentPrice(product).multiply(100 + percentage).divide(100);
        return setPrice(product, newPrice, effectiveTime);
    }

    /**
     * 按百分比对一批产品调整价格，在指定时间生效
     * @param products 要设定价格的商品
     * @param percentage 要调整的单价的百分比。例如10就是价格上调10%，-5就是下调5%
     * @param effectiveTime 生效时间
     * @return 一批定价对象
     */
    public Set<Pricing> adjustPriceByPercentage(Set<Product> products, int percentage, LocalDateTime effectiveTime) {
        return products.stream()
                .map(product -> adjustPriceByPercentage(product, percentage, effectiveTime))
                .collect(Collectors.toSet());
    }

    /**
     * 获取特定时刻的商品价格
     * @param product 要查询价格的商品
     * @param time 要查询的时刻
     * @return 指定商品在指定时刻的单价
     * @throws PricingException 当商品还没设定单价时抛出此异常
     */
    public Money priceAt(Product product, LocalDateTime time) {
        return pricings.getPricingAt(product, time)
                .map(Pricing::getUnitPrice)
                .orElseThrow(() -> new PricingException(product.getName() + "'s price has not been set yet."));
    }

    /**
     * 获得一批商品在指定时间的定价信息
     * @param products 商品
     * @param time 时间
     * @return 当时的商品价格
     */
    public Map<Product, Money> getPricingAt(Set<Product> products, LocalDateTime time) {
        return products.stream()
                .map(product -> pricings.getPricingAt(product, time))
                .map(Optional::get)
                .collect(Collectors.toMap(Pricing::getProduct, Pricing::getUnitPrice));
    }

    /**
     * 获取商品的当前价格
     * @param product 要查询价格的商品
     * @return 商品的当前价格
     */
    public Money currentPrice(Product product) {
        return priceAt(product, LocalDateTime.now());
    }

    /**
     * 获取商品的定价历史
     * @param product 要查询的商品
     * @return 该商品的定价历史，按定价时间排序
     */
    public Stream<Pricing> pricingHistory(Product product) {
        return pricings.findPricingHistoryOfProduct(product);
    }

}
