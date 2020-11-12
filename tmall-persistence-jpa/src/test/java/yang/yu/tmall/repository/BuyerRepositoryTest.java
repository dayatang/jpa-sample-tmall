package yang.yu.tmall.repository;

import org.junit.jupiter.api.*;
import yang.yu.tmall.domain.Buyer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
        buyer1 = new Buyer("buyer1");
        buyer2 = new Buyer("buyer2");
        buyer1 = repository.save(new Buyer("buyer1"));
        buyer2 = repository.save(new Buyer("buyer2"));
        assertThat(buyer1.getId()).isGreaterThan(0);
        assertThat(buyer2.getId()).isGreaterThan(0);
    }

}