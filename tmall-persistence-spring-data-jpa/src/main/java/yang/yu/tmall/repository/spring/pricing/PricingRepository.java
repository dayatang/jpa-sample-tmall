package yang.yu.tmall.repository.spring.pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.pricing.Pricing;
import yang.yu.tmall.domain.pricing.Pricings;
import yang.yu.tmall.domain.products.Product;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 定价仓储的实现
 */
@Named
public interface PricingRepository extends Pricings, JpaRepository<Pricing, Integer> {

    @Override
    default Optional<Pricing> getPricingAt(Product product, LocalDateTime time) {
        return findFirstByProductAndEffectiveTimeIsLessThanEqualOrderByEffectiveTimeDesc(product, time);
    }

    Optional<Pricing> findFirstByProductAndEffectiveTimeIsLessThanEqualOrderByEffectiveTimeDesc(Product product, LocalDateTime time);

    @Override
    default Stream<Pricing> findPricingHistoryByProduct(Product product) {
        return findByProductOrderByEffectiveTime(product);
    }

    Stream<Pricing> findByProductOrderByEffectiveTime(Product product);
}
