package yang.yu.tmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.Pricing;
import yang.yu.tmall.domain.Pricings;
import yang.yu.tmall.domain.Product;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Optional;

@Named
public interface PricingRepository extends Pricings, JpaRepository<Pricing, Integer> {

    @Override
    default Optional<Pricing> findLastByProduct(Product product, LocalDateTime time) {
        return findFirstByProductAndEffectiveTimeBeforeOrderByEffectiveTime(product, time);
    }

    Optional<Pricing> findFirstByProductAndEffectiveTimeBeforeOrderByEffectiveTime(Product product, LocalDateTime time);
}
