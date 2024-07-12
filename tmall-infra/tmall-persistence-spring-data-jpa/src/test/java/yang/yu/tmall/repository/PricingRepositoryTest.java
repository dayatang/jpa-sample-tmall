package yang.yu.tmall.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.pricing.Pricing;
import yang.yu.tmall.domain.pricing.Pricings;
import yang.yu.tmall.domain.catalog.Product;
import yang.yu.tmall.spring.JpaSpringConfig;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringJUnitConfig(classes = JpaSpringConfig.class)
@Transactional
public class PricingRepositoryTest implements WithAssertions {

    @Inject
    private Pricings pricings;

    @Inject
    private EntityManager entityManager;

    private Product product1, product2;

    private Pricing pricing1, pricing2, pricing3, pricing4;

    @BeforeEach
    void beforeEach() {
        product1 = entityManager.merge(new Product("电冰箱", null));
        product2 = entityManager.merge(new Product("电视机", null));
        pricing1 = entityManager.merge(new Pricing(product1, Money.valueOf(500), LocalDate.of(2020,10, 1).atStartOfDay()));
        pricing2 = entityManager.merge(new Pricing(product1, Money.valueOf(600), LocalDate.of(2020,2, 15).atStartOfDay()));
        pricing3 = entityManager.merge(new Pricing(product2, Money.valueOf(7000), LocalDate.of(2020,7, 14).atStartOfDay()));
        pricing4 = entityManager.merge(new Pricing(product2, Money.valueOf(7100), LocalDate.of(2020,2, 15).atStartOfDay()));
    }


    @AfterEach
    void afterEach() {
        Arrays.asList(product1, product2, pricing1, pricing2, pricing3, pricing4)
                .forEach(entityManager::remove);
    }

    @Test
    void getPriceAt() {
        LocalDateTime time2002_02_15 = LocalDate.of(2020, 2, 15).atStartOfDay();
        LocalDateTime time2002_02_16 = LocalDate.of(2020, 2, 16).atStartOfDay();
        LocalDateTime time2002_10_01 = LocalDate.of(2020, 10, 1).atStartOfDay();
        assertThat(pricings.getPricingAt(product1, time2002_02_15))
                .map(Pricing::getUnitPrice)
                .contains(Money.valueOf(600));
        assertThat(pricings.getPricingAt(product1, time2002_02_16))
                .map(Pricing::getUnitPrice)
                .contains(Money.valueOf(600));
        assertThat(pricings.getPricingAt(product1, time2002_10_01))
                .map(Pricing::getUnitPrice)
                .contains(Money.valueOf(500));
    }

}
