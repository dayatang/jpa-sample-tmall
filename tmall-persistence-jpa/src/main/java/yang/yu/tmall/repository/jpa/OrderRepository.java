package yang.yu.tmall.repository.jpa;

import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.sales.OrderLine;
import yang.yu.tmall.domain.sales.Orders;

import javax.persistence.EntityManager;
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
        entityManager.createQuery("select o from OrderLine o", OrderLine.class)
                .getResultStream()
                .forEach(System.out::println);

        String jpql = "select o.order from OrderLine o where o.product = :product order by o.order.created desc ";
        //String jpql = "select o from Order o join o.lineItems i where i.product = :product";
        return entityManager.createQuery(jpql, Order.class)
                .setParameter("product", product)
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
