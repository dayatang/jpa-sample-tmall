package yang.yu.tmall.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yang.yu.tmall.domain.buyers.*;
import yang.yu.tmall.repository.jpa.BuyerRepositoryJpql;

class BuyerRepositoryJpqlTest extends BaseIntegrationTest {

    private static final String buyer1Name = "张三";

    private static final String buyer2Name = "华为公司";

    private Buyers buyers;

    private PersonalBuyer buyer1;

    private OrgBuyer buyer2;

    @BeforeEach
    void beforeEach() {
        buyers = new BuyerRepositoryJpql(entityManager);
        buyer1 = buyers.save(new PersonalBuyer(buyer1Name));
        buyer2 = buyers.save(new OrgBuyer(buyer2Name));
    }

    @AfterEach
    void afterEach() {
        buyers.findAll().forEach(buyers::delete);
    }

    @Test
    void findById() {
        assertThat(buyers.getById(buyer1.getId())).containsSame(buyer1);
        assertThat(buyers.getById(buyer2.getId())).containsSame(buyer2);
    }

    @Test
    void findByName() {
        assertThat(buyers.getByName(buyer1Name)).containsSame(buyer1);
        assertThat(buyers.getByName(buyer2Name)).containsSame(buyer2);
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
    void delete() {
        buyers.delete(buyer1);
        assertThat(buyers.findAll()).contains(buyer2).doesNotContain(buyer1);
    }

    @Test
    void update() {
        buyer1.setName("李四");
        buyers.save(buyer1);
        assertThat(buyers.getById(buyer1.getId()).map(Buyer::getName)).containsSame("李四");
        assertThat(buyers.getById(buyer2.getId()).map(Buyer::getName)).containsSame(buyer2Name);
    }

    @Test
    void findPersonalBuyerByQQ() {
        buyer1.setImInfo(ImType.QQ, "34567");
        buyers.save(buyer1);
        assertThat(buyers.findPersonalBuyerByQQ("34567")).containsSame(buyer1);
    }


}