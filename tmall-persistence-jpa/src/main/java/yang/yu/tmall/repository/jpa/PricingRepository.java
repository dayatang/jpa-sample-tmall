package yang.yu.tmall.repository.jpa;

import yang.yu.tmall.domain.pricing.Pricing;
import yang.yu.tmall.domain.pricing.Pricings;
import yang.yu.tmall.domain.catalog.Product;

import javax.inject.Named;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Named
public class PricingRepository implements Pricings {

    private final EntityManager entityManager;

    public PricingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Pricing save(Pricing pricing) {
        return entityManager.merge(pricing);
    }

    @Override
    public Optional<Pricing> getPricingAt(Product product, LocalDateTime time) {
        String jpql = "select o from Pricing o where o.product = :product and o.effectiveTime <= :time order by o.effectiveTime desc";
        List<Pricing> resultList = entityManager.createQuery(jpql, Pricing.class)
                .setParameter("product", product)
                .setParameter("time", time)
                .getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Override
    public Stream<Pricing> findPricingHistoryOfProduct(Product product) {
        String jpql = "select o from Pricing o where o.product = :product order by o.effectiveTime";
        return entityManager.createQuery(jpql, Pricing.class)
                .setParameter("product", product)
                .getResultStream();
    }
}
