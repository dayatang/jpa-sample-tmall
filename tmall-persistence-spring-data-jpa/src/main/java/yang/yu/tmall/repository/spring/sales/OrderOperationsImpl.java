package yang.yu.tmall.repository.spring.sales;

import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.products.Product;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class OrderOperationsImpl implements OrderOperations {

    private EntityManager entityManager;

    public OrderOperationsImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
                " and o.created >= :fromTime and o.created < :untilTime " +
                " order by o.order.created desc ";
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
}
