package yang.yu.tmall.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class BuyerRepositoryTest {

    private static EntityManagerFactory emf;

    private EntityManager entityManager;

    private Buyer buyer1;

    private Buyer buyer2;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    @BeforeEach
    void beforeEach() {
        entityManager = emf.createEntityManager();
    }

    @Test
    void create() {
        buyer1 = new Buyer("buyer1");
        buyer2 = new Buyer("buyer2");

    }

}