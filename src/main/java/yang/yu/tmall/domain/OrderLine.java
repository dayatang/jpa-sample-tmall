package yang.yu.tmall.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_lines")
public class OrderLine extends BaseEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private BigDecimal quantity;

    @AttributeOverride(name = "amount", column = @Column(name = "unit_price"))
    private Money unitPrice;

    private BigDecimal discountRate;

    @AttributeOverride(name = "amount", column = @Column(name = "sub_total"))
    private Money subTotal;

    public OrderLine() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Money subTotal) {
        this.subTotal = subTotal;
    }

    public void calculateSubTotal() {
        this.subTotal = unitPrice.multiply(quantity).multiply(discountRate).divide(100);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderLine)) {
            return false;
        }
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(getProduct(), orderLine.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct());
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", discountRate=" + discountRate +
                ", subTotal=" + subTotal +
                '}';
    }
}
