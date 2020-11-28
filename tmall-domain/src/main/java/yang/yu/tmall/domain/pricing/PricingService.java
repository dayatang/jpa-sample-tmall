package yang.yu.tmall.domain.pricing;

import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.products.Products;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Set;

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

    public void adjustPriceByPercentage(Set<Product> products, int percentage, LocalDateTime effectiveTime) {
        products.forEach(product -> adjustPriceByPercentage(product, percentage, effectiveTime));
    }

    public Money getPriceAt(Product product, LocalDateTime time) {
        return pricings.getPricingAt(product, time)
                .map(Pricing::getUnitPrice)
                .orElseThrow(() -> new PricingException(product.getName() + "'s price has not been set yet."));
    }

    public Money getCurrentPrice(Product product) {
        return pricings.getPricingAt(product, LocalDateTime.now())
                .map(Pricing::getUnitPrice)
                .orElseThrow(() -> new PricingException(product.getName() + "'s price has not been set yet."));

    }
}
