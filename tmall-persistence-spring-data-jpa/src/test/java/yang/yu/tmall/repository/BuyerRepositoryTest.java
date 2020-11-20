package yang.yu.tmall.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import yang.yu.tmall.domain.*;
import yang.yu.tmall.spring.JpaSpringConfig;

import javax.inject.Inject;
import javax.transaction.Transactional;

@SpringJUnitConfig(classes = JpaSpringConfig.class)
@Transactional
class BuyerRepositoryTest implements WithAssertions {

    private static final String buyer1Name = "张三";

    private static final String buyer2Name = "华为公司";

    @Inject
    private Buyers buyers;

    private PersonalBuyer buyer1;

    private OrgBuyer buyer2;

    @BeforeEach
    void beforeEach() {
        buyer1 = buyers.save(new PersonalBuyer(buyer1Name));
        buyer2 = buyers.save(new OrgBuyer(buyer2Name));
    }

    @AfterEach
    void afterEach() {
        buyers.findAll().forEach(buyers::delete);
    }

    @Test
    void findById() {
        assertThat(buyers.getById(buyer1.getId()).get()).isEqualTo(buyer1);
        assertThat(buyers.getById(buyer2.getId()).get()).isEqualTo(buyer2);
    }

    @Test
    void findByName() {
        assertThat(buyers.getByName(buyer1Name).get()).isEqualTo(buyer1);
        assertThat(buyers.getByName(buyer2Name).get()).isEqualTo(buyer2);
    }

    @Test
    void findByNameStartsWith() {
        assertThat(buyers.findByNameStartsWith("华"))
                .contains(buyer2)
                .doesNotContain(buyer1);
        assertThat(buyers.findByNameStartsWith("三"))
                .isEmpty();
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
    void findPersonalBuyerByQQ() {
        buyer1.setImInfo(ImType.QQ, "34567");
        buyers.save(buyer1);
        assertThat(buyers.findPersonalBuyerByQQ("34567").get()).isEqualTo(buyer1);
    }

    @Test
    void delete() {
        buyers.delete(buyer1);
        assertThat(buyers.findAll()).contains(buyer2).doesNotContain(buyer1);
    }

    @Test
    void update() {
        buyer1.setName("李四");
        buyers.save(buyer1);
        assertThat(buyers.getById(buyer1.getId()).get().getName()).isEqualTo("李四");
        assertThat(buyers.getById(buyer2.getId()).get().getName()).isEqualTo(buyer2Name);
    }
}