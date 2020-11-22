package yang.yu.tmall.domain;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
public class PricingService {

    private Pricings pricings;

    public PricingService(Pricings pricings) {
        this.pricings = pricings;
    }

    Pricing setUnitPriceOfProduct(Product product, Money unitPrice, LocalDateTime effectiveFrom) {
        return pricings.save(new Pricing(product, unitPrice, effectiveFrom));
    }

    public Money getPriceAt(Product product, LocalDateTime time) {
        return pricings.findLastByProduct(product, time)
                .map(Pricing::getUnitPrice)
                .orElseThrow(() -> new PricingException(product.getName() + "' price has not been set yet"));
    }

    public Money getCurrentPrice(Product product) {
        return pricings.findLastByProduct(product, LocalDateTime.now())
                .map(Pricing::getUnitPrice)
                .orElseThrow(() -> new PricingException(product.getName() + "' price has not been set yet"));

    }
}
