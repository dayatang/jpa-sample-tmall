package yang.yu.tmall.repository.jpa;

import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.catalog.Product;
import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.sales.Orders;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

public class OrderRepository implements Orders {

    private final EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Order> getById(int id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public Optional<Order> getByOrderNo(String orderNo) {
        return entityManager.createQuery("select o from Order o where o.orderNo = :orderNo", Order.class)
                .setParameter("orderNo", orderNo)
                .getResultStream()
                .findAny();
    }

    @Override
    public Stream<Order> findByBuyer(Buyer buyer) {
        return entityManager.createQuery("select o from Order o where o.buyer = :buyer", Order.class)
                .setParameter("buyer", buyer)
                .getResultStream();
    }

    @Override
    public Stream<Order> findByProduct(Product product) {
        String jpql = "select o.order from OrderLine o where o.product = :product order by o.order.created desc ";
        return entityManager.createQuery(jpql, Order.class)
                .setParameter("product", product)
                .getResultStream();
    }

    @Override
    public Stream<Order> findByProduct(Product product, LocalDateTime from, LocalDateTime until) {
        String jpql = "select o.order from OrderLine o where o.product = :product " +
                "and o.created >= :fromTime and o.created < :untilTime order by o.order.created desc ";
        return entityManager.createQuery(jpql, Order.class)
                .setParameter("product", product)
                .setParameter("fromTime", from)
                .setParameter("untilTime", until)
                .getResultStream();
    }

    @Override
    public Stream<Order> findByOrgBuyers() {
        String jpql = "select o from Order o join o.buyer b where TYPE(b) = OrgBuyer";
        return entityManager.createQuery(jpql, Order.class)
                .getResultStream();
    }

    @Override
    public Order save(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public void delete(Order order) {
        entityManager.remove(order);
    }
}
