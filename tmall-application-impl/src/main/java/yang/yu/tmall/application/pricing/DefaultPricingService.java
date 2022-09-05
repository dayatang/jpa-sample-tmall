package yang.yu.tmall.application.pricing;


import yang.yu.tmall.application.PricingService;
import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.catalogue.Product;
import yang.yu.tmall.domain.catalogue.Products;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 定价服务的实现
 */
public class DefaultPricingService implements PricingService {

    private final yang.yu.tmall.domain.pricing.PricingService service;
    private final Products products;

    /**
     * 构造函数
     * @param service 定价领域服务
     * @param products 商品仓储
     */
    public DefaultPricingService(yang.yu.tmall.domain.pricing.PricingService service,
                                 Products products) {
        this.service = service;
        this.products = products;
    }

    @Override
    public void setPrice(int productId, BigDecimal unitPrice, LocalDateTime effectiveTime) {
        service.setPrice(getProduct(productId), Money.valueOf(unitPrice), effectiveTime);
    }

    @Override
    public void adjustPriceByPercentage(int productId, int percentage, LocalDateTime effectiveTime) {
        service.adjustPriceByPercentage(getProduct(productId), percentage, effectiveTime);
    }

    @Override
    public void adjustPriceByPercentage(Set<Integer> productIds, int percentage, LocalDateTime effectiveTime) {
        Set<Product> productSet = productIds.stream()
          .map(this::getProduct)
          .collect(Collectors.toSet());
        service.adjustPriceByPercentage(productSet, percentage, effectiveTime);
    }

    @Override
    public BigDecimal priceAt(int productId, LocalDateTime time) {
        return service.priceAt(getProduct(productId), time).getValue();
    }

    @Override
    public Map<Integer, BigDecimal> priceAt(Set<Integer> productIds, LocalDateTime time) {
        Set<Product> productSet = productIds.stream().map(this::getProduct).collect(Collectors.toSet());
        return service.getPricingAt(productSet, time)
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getId(),
                        entry -> entry.getValue().getValue()));
    }

    @Override
    public BigDecimal currentPrice(int productId) {
        return service.currentPrice(getProduct(productId)).getValue();
    }

    @Override
    public Map<Integer, BigDecimal> currentPrice(Set<Integer> productIds) {
        return priceAt(productIds, LocalDateTime.now());
    }

    private Product getProduct(int productId) {
        return products.getById(productId)
                .orElseThrow(() -> new RuntimeException("Product ID " + productId + " not found!"));
    }
}
