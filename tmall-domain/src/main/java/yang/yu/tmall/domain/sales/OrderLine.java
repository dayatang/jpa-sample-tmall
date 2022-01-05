package yang.yu.tmall.domain.sales;

import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.commons.BaseEntity;
import yang.yu.tmall.domain.commons.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_lines")
public class OrderLine extends BaseEntity {

    @ManyToOne(optional = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Product product;

    private BigDecimal quantity = BigDecimal.ZERO;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "unit_price"))
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "unit_price"))
    })
    private Money unitPrice;

    @Column(name = "discount_rate")
    private BigDecimal discountRate = BigDecimal.ZERO;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sub_total"))
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "sub_total"))
    })
    private Money subTotal;

    public OrderLine() {
    }

    public OrderLine(Product product, BigDecimal quantity, Money unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        calculateSubTotal();
    }

    public OrderLine(Product product, double quantity, Money unitPrice) {
        this(product, BigDecimal.valueOf(quantity), unitPrice);
    }

    public OrderLine(Product product, double quantity) {
        this(product, quantity, Money.ZERO);
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
        calculateSubTotal();
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
        calculateSubTotal();
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
        calculateSubTotal();
    }

    public Money getSubTotal() {
        if (isNew()) {
            calculateSubTotal();
        }
        return subTotal;
    }

    @PreUpdate
    void calculateSubTotal() {
        Money base = unitPrice.multiply(quantity);
        Money discountMoney = base.multiply(discountRate).divide(100);
        this.subTotal = base.subtract(discountMoney);
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
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
