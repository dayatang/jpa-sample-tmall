package yang.yu.tmall.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "order_no")
    private String orderNo;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "seq_no")
    private List<OrderLine> lineItems = new ArrayList<>();

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

    public List<OrderLine> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    public void setLineItems(List<OrderLine> lineItems) {
        this.lineItems = new ArrayList<>(lineItems);
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
