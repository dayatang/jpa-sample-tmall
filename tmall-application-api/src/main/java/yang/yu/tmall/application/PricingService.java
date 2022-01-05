package yang.yu.tmall.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface PricingService {

    void setPrice(int productId, BigDecimal unitPrice, LocalDateTime effectiveTime);

    void adjustPriceByPercentage(int productId, int percentage, LocalDateTime effectiveTime);

    void adjustPriceByPercentage(Set<Integer> productIds, int percentage, LocalDateTime effectiveTime);

    BigDecimal priceAt(int productId, LocalDateTime time);

    Map<Integer, BigDecimal> priceAt(Set<Integer> productIds, LocalDateTime time);

    BigDecimal currentPrice(int productId);

    Map<Integer, BigDecimal> currentPrice(Set<Integer> productIds);
}
