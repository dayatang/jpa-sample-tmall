package yang.yu.tmall.repository.spring.pricing;

import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.products.Product;

import java.time.LocalDateTime;
import java.util.Map;

public interface PricingQueryOperations {
    Map<Product, Money> getAllProductPriceAt(LocalDateTime time);
}
