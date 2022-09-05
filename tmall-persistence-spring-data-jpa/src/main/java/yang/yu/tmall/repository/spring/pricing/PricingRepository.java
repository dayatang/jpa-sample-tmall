package yang.yu.tmall.repository.spring.pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.pricing.Pricing;
import yang.yu.tmall.domain.pricing.Pricings;
import yang.yu.tmall.domain.catalogue.Product;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 定价仓储的实现
 */
public interface PricingRepository extends Pricings, JpaRepository<Pricing, Integer> {

    @Override
    default Optional<Pricing> getPricingAt(Product product, LocalDateTime time) {
        return findFirstByProductAndEffectiveTimeIsLessThanEqualOrderByEffectiveTimeDesc(product, time);
    }

    @Override
    default Stream<Pricing> findPricingHistoryOfProduct(Product product) {
        return findByProductOrderByEffectiveTime(product);
    }

    Optional<Pricing> findFirstByProductAndEffectiveTimeIsLessThanEqualOrderByEffectiveTimeDesc(Product product, LocalDateTime time);

    Stream<Pricing> findByProductOrderByEffectiveTime(Product product);
}
