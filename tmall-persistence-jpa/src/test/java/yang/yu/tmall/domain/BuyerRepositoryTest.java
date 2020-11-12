package yang.yu.tmall.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class BuyerRepositoryTest implements WithAssertions {

    private static EntityManagerFactory emf;

    private EntityManager entityManager;

    private EntityTransaction transaction;

    private Buyer buyer1;

    private Buyer buyer2;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    @BeforeEach
    void beforeEach() {
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterEach
    void tearDown() {
        transaction.rollback();
        entityManager.clear();
    }

    @AfterAll
    static void afterAll() {
        emf.close();
    }

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