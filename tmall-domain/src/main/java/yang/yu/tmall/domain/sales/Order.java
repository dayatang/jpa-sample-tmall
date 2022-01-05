package yang.yu.tmall.domain.sales;

import yang.yu.lang.IoC;
import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.commons.Address;
import yang.yu.tmall.domain.commons.BaseEntity;
import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.products.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    protected Order() {
    }

    public Order(String orderNo, List<OrderLine> lineItems, Buyer buyer, Address shippingAddress) {
        this.orderNo = orderNo;
        this.lineItems = lineItems;
        this.buyer = buyer;
        this.shippingAddress = shippingAddress;
    }

    @Basic(optional = false)
    @Column(name = "order_no", nullable = false, unique = true)
    private String orderNo;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "seq_no")
    private List<OrderLine> lineItems = new ArrayList<>();

    @ManyToOne
    private Buyer buyer;

    @Embedded
    private Address shippingAddress;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "total_price"))
    private Money totalPrice;

    public String getOrderNo() {
        return orderNo;
    }

    public List<OrderLine> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    public void addLineItem(OrderLine lineItem) {
        if (lineItem == null) {
            return;
        }
        if (containsProduct(lineItem.getProduct())) {
            throw new DuplicateOrderLineException();
        }
        lineItem.setOrder(this);
        lineItems.add(lineItem);
        calculateTotalPrice();
    }

    private boolean containsProduct(Product product) {
        return lineItems.stream()
                .map(OrderLine::getProduct)
                .anyMatch(product::equals);
    }

    public void removeLineItem(OrderLine lineItem) {
        lineItem.setOrder(null);
        lineItems.remove(lineItem);
        calculateTotalPrice();
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void beforeCreate() {
        super.beforeCreate();
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        this.totalPrice = lineItems.stream()
                .map(OrderLine::getSubTotal)
                .peek(System.out::println)
                .reduce(Money.ZERO, Money::add);
    }

    public void save() {
        getOrders().save(this);
    }

    private Orders getOrders() {
        return IoC.getInstance(Orders.class);
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
