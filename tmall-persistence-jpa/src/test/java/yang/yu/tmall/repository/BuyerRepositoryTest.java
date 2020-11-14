package yang.yu.tmall.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yang.yu.tmall.domain.Buyer;
import yang.yu.tmall.domain.OrgBuyer;
import yang.yu.tmall.domain.PersonalBuyer;

class BuyerRepositoryTest extends BaseIntegrationTest {

    private BuyerRepository repository;

    private Buyer buyer1;

    private Buyer buyer2;

    @BeforeEach
    void beforeEach() {
        System.out.println("child beforeEach");
        getEntityManager().getClass();
        repository = new BuyerRepository(getEntityManager());
    }


    @Test
    void create() {
        buyer1 = repository.save(new PersonalBuyer("张三"));
        buyer2 = repository.save(new OrgBuyer("华为公司"));
        assertThat(buyer1.getId()).isGreaterThan(0);
        assertThat(buyer2.getId()).isGreaterThan(0);
    }

}