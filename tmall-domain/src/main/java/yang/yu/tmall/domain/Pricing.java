package yang.yu.tmall.domain;

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
    private Product product;

    private Money unitPrice;

    @Column(name = "pricing_time")
    private LocalDateTime pricingTime;

    public Pricing() {
    }

    public Pricing(Product product, Money unitPrice, LocalDateTime pricingTime) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.pricingTime = pricingTime;
    }

    public Pricing(Product product, Money unitPrice) {
        this(product, unitPrice, LocalDateTime.now());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getPricingTime() {
        return pricingTime;
    }

    public void setPricingTime(LocalDateTime pricingTime) {
        this.pricingTime = pricingTime;
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
                getPricingTime().equals(pricing.getPricingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getPricingTime());
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "product=" + product +
                ", unitPrice=" + unitPrice +
                ", pricingTime=" + pricingTime +
                '}';
    }
}
