package yang.yu.tmall.domain.pricing;

import yang.yu.tmall.domain.products.Product;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Pricings {

    Pricing save(Pricing pricing);

    Optional<Pricing> getPricingAt(Product product, LocalDateTime time);

}
