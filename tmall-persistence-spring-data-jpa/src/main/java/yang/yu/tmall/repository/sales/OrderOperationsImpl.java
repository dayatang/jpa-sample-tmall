package yang.yu.tmall.repository.sales;

import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.products.Product;

import javax.persistence.EntityManager;
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
}
