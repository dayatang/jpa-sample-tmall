package yang.yu.tmall.domain;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
public class PricingService {

    private Pricings pricings;

    public PricingService(Pricings pricings) {
        this.pricings = pricings;
    }

    public Pricing setUnitPrice(Product product, Money unitPrice, LocalDateTime effectiveTime) {
        return pricings.save(new Pricing(product, unitPrice, effectiveTime));
    }

    public Pricing adjustPriceByPercentage(Product product, int percentage, LocalDateTime effectiveTime) {
        Money newPrice = getCurrentPrice(product).multiply(100 + percentage).divide(100);
        return setUnitPrice(product, newPrice, effectiveTime);
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
