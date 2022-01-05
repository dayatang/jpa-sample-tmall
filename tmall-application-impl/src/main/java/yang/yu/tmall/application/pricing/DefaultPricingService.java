package yang.yu.tmall.application.pricing;


import yang.yu.tmall.application.PricingService;
import yang.yu.tmall.domain.products.Products;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class DefaultPricingService implements PricingService {

    private final yang.yu.tmall.domain.pricing.PricingService service;
    private final Products products;

    public DefaultPricingService(yang.yu.tmall.domain.pricing.PricingService service,
                                 Products products) {
        this.service = service;
        this.products = products;
    }

    @Override
    public void setPrice(int productId, BigDecimal unitPrice, LocalDateTime effectiveTime) {

    }

    @Override
    public void adjustPriceByPercentage(int productId, int percentage, LocalDateTime effectiveTime) {

    }

    @Override
    public void adjustPriceByPercentage(Set<Integer> productIds, int percentage, LocalDateTime effectiveTime) {

    }

    @Override
    public BigDecimal priceAt(int productId, LocalDateTime time) {
        return null;
    }

    @Override
    public Map<Integer, BigDecimal> priceAt(Set<Integer> productIds, LocalDateTime time) {
        return null;
    }

    @Override
    public BigDecimal currentPrice(int productId) {
        return null;
    }

    @Override
    public Map<Integer, BigDecimal> currentPrice(Set<Integer> productIds) {
        return null;
    }
}
