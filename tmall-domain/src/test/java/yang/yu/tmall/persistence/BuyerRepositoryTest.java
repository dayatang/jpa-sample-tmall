package yang.yu.tmall.persistence;

import org.junit.jupiter.api.Test;
import yang.yu.tmall.domain.Buyer;
import yang.yu.tmall.domain.OrgBuyer;
import yang.yu.tmall.domain.PersonalBuyer;

class BuyerRepositoryTest extends BaseIntegrationTest {

    private Buyer buyer1;

    private Buyer buyer2;

    @Test
    void create() {
        buyer1 = new PersonalBuyer("张三");
        buyer2 = new OrgBuyer("华为公司");
        entityManager.persist(buyer1);
        entityManager.persist(buyer2);
        assertThat(buyer1.getId()).isGreaterThan(0);
        assertThat(buyer2.getId()).isGreaterThan(0);
    }

}