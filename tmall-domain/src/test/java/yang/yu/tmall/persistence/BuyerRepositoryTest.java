package yang.yu.tmall.persistence;

import org.junit.jupiter.api.Test;
import yang.yu.tmall.domain.Buyer;

class BuyerRepositoryTest extends BaseIntegrationTest {

    private Buyer buyer1;

    private Buyer buyer2;

    @Test
    void create() {
        buyer1 = new Buyer("buyer1");
        buyer2 = new Buyer("buyer2");
        entityManager.persist(buyer1);
        entityManager.persist(buyer2);
        assertThat(buyer1.getId()).isGreaterThan(0);
        assertThat(buyer2.getId()).isGreaterThan(0);
    }

}