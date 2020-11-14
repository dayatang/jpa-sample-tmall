package yang.yu.tmall.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private String orderNo;

    private LocalDateTime createdOn = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLine> lineItems = new HashSet<>();

    @ManyToOne
    private Buyer buyer;

    private Address shippingAddress;

    @AttributeOverride(name = "value", column = @Column(name = "total_price"))
    private Money totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Set<OrderLine> getLineItems() {
        return new HashSet<>(lineItems);
    }

    public void setLineItems(Set<OrderLine> lineItems) {
        this.lineItems = new HashSet<>(lineItems);
        calculateTotalPrice();
    }

    public void addLineItem(OrderLine lineItem) {
        lineItem.setOrder(this);
        lineItems.add(lineItem);
        calculateTotalPrice();
    }

    public void removeLineItem(OrderLine lineItem) {
        lineItem.setOrder(null);
        lineItems.remove(lineItem);
        calculateTotalPrice();
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    private Money calculateTotalPrice() {
        this.totalPrice = lineItems.stream()
                .map(OrderLine::getSubTotal)
                .peek(System.out::println)
                .reduce(Money.ZERO, Money::add);
        return this.totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(getOrderNo(), order.getOrderNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNo());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                '}';
    }
}
