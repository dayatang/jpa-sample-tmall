package yang.yu.tmall.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import yang.yu.tmall.spring.JpaSpringConfig;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@SpringJUnitConfig(classes = JpaSpringConfig.class)
@Transactional
class BuyerRepositoryTest implements WithAssertions {

    private static final String buyer1Name = "张三";

    private static final String buyer2Name = "华为公司";

    @Inject
    private Buyers buyers;

    private Buyer buyer1;

    private Buyer buyer2;

    @BeforeEach
    void beforeEach() {
        buyer1 = buyers.save(new PersonalBuyer(buyer1Name));
        buyer2 = buyers.save(new OrgBuyer(buyer2Name));
    }

    @AfterEach
    void afterEach() {
        buyers.deleteAll();
    }

    @Test
    void findById() {
        assertThat(buyers.findById(buyer1.getId()).get()).isEqualTo(buyer1);
        assertThat(buyers.findById(buyer2.getId()).get()).isEqualTo(buyer2);
    }

    @Test
    void findByName() {
        assertThat(buyers.findByName(buyer1Name).get()).isEqualTo(buyer1);
        assertThat(buyers.findByName(buyer2Name).get()).isEqualTo(buyer2);
    }

    @Test
    void findByNameContains() {
        assertThat(buyers.findByNameContains("三"))
                .contains(buyer1)
                .doesNotContain(buyer2);
    }

    @Test
    void findAll() {
        assertThat(buyers.findAll()).contains(buyer1, buyer2);
    }

    @Test
    void delete() {
        buyers.delete(buyer1);
        assertThat(buyers.findAll()).contains(buyer2).doesNotContain(buyer1);
    }
}