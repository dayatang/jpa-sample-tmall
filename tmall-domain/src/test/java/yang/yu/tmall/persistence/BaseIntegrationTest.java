package yang.yu.tmall.persistence;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class BaseIntegrationTest implements WithAssertions {

    private static EntityManagerFactory emf;

    protected EntityManager entityManager;

    protected EntityTransaction transaction;

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

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityTransaction getTransaction() {
        return transaction;
    }
}
