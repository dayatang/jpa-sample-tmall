package yang.yu.tmall.repository.spring.pricing;

import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.products.Product;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class PricingQueryOperationsImpl implements PricingQueryOperations {

    private EntityManager entityManager;

    public PricingQueryOperationsImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Map<Product, Money> getAllProductPriceAt(LocalDateTime time) {
        String jpql = "select o.product, first(o.unitPrice) from Pricing o" +
                " where effectiveTime <= :time" +
                " group by o.product" +
                " order by effectiveTime desc";
        return entityManager.createQuery(jpql, Object[].class)
                .setParameter("time", time)
                .getResultStream()
                .map(Pair::new)
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private class Pair {
        private Product key;
        private Money value;
        Pair(Object[] pair) {
            key = (Product) pair[0];
            value = (Money) pair[1];
        }

        public Product getKey() {
            return key;
        }

        public Money getValue() {
            return value;
        }
    }
}
