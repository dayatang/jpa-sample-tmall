package yang.yu.tmall.repository;

import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.buyers.Buyers;
import yang.yu.tmall.domain.buyers.ImType;
import yang.yu.tmall.domain.buyers.PersonalBuyer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BuyerRepositoryCriteria implements Buyers {

    private final EntityManager entityManager;

    public BuyerRepositoryCriteria(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T extends Buyer> T save(T buyer) {
        return entityManager.merge(buyer);
    }

    @Override
    public void delete(Buyer buyer) {
        entityManager.remove(buyer);
    }

    @Override
    public List<Buyer> findAll() {
        CriteriaQuery<Buyer> query = createCriteriaQuery(Buyer.class);
        return entityManager.createQuery(query.select(query.from(Buyer.class))).getResultList();
    }

    @Override
    public Optional<Buyer> getById(int id) {
        return Optional.ofNullable(entityManager.find(Buyer.class, id));
    }

    @Override
    public Optional<Buyer> getByName(String name) {
        CriteriaQuery<Buyer> query = createCriteriaQuery(Buyer.class);
        Root<Buyer> root = query.from(Buyer.class);
        Predicate predicate = getCriteriaBuilder().equal(root.get("name"), name);
        return entityManager
                .createQuery(query.select(root).where(predicate))
                .getResultStream()
                .findAny();
    }

    @Override
    public Stream<Buyer> findByNameStartsWith(String nameFragment) {
        CriteriaQuery<Buyer> query = createCriteriaQuery(Buyer.class);
        Root<Buyer> root = query.from(Buyer.class);
        Predicate predicate = getCriteriaBuilder().like(root.get("name"), nameFragment + "%");
        return entityManager
                .createQuery(query.select(root).where(predicate))
                .getResultStream();
    }

    @Override
    public Stream<Buyer> findByNameContains(String nameFragment) {
        CriteriaQuery<Buyer> query = createCriteriaQuery(Buyer.class);
        Root<Buyer> root = query.from(Buyer.class);
        Predicate predicate = getCriteriaBuilder().like(root.get("name"), "%" + nameFragment + "%");
        return entityManager
                .createQuery(query.select(root).where(predicate))
                .getResultStream();
    }

    @Override
    public Optional<PersonalBuyer> findPersonalBuyerByQQ(String qq) {
        String jpql = "select o from PersonalBuyer o join o.imInfos i where KEY(i) = :key and VALUE(i) = :value";
        return entityManager.createQuery(jpql, PersonalBuyer.class)
                .setParameter("key", ImType.QQ)
                .setParameter("value", qq)
                .getResultStream()
                .findAny();
    }

    private CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    private <T extends Buyer> CriteriaQuery<T> createCriteriaQuery(Class<T> resultClass) {
        return getCriteriaBuilder().createQuery(resultClass);
    }
}
