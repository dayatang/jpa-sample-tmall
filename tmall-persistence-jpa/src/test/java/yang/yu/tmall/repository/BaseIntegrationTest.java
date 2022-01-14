package yang.yu.tmall.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Transactional
public abstract class BaseIntegrationTest implements WithAssertions {

    private static EntityManagerFactory emf;

    protected EntityManager entityManager;

    private EntityTransaction transaction;

    @BeforeAll
    static void beforeAllTest() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    @BeforeEach
    void BeforeEachTest() {
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("parent beforeEach");
    }

    @AfterEach
    void afterEachTest() {
        transaction.rollback();
        entityManager.clear();
    }

    @AfterAll
    static void afterAllTest() {
        emf.close();
    }
}
