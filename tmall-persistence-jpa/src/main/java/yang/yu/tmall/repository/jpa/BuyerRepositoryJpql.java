package yang.yu.tmall.repository.jpa;

import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.buyers.Buyers;
import yang.yu.tmall.domain.buyers.ImType;
import yang.yu.tmall.domain.buyers.PersonalBuyer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BuyerRepositoryJpql implements Buyers {

    private final EntityManager entityManager;

    public BuyerRepositoryJpql(EntityManager entityManager) {
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
        return entityManager.createQuery("select o from Buyer o").getResultList();
    }

    @Override
    public Optional<Buyer> getById(int id) {
        return Optional.ofNullable(entityManager.find(Buyer.class, id));
    }

    @Override
    public Optional<Buyer> getByName(String name) {
        return entityManager
                .createQuery("select o from Buyer o where o.name = :name", Buyer.class)
                .setParameter("name", name)
                .getResultStream()
                .findAny();
    }

    @Override
    public Stream<Buyer> findByNameStartsWith(String nameFragment) {
        return entityManager
                .createQuery("select o from Buyer o where o.name Like :name", Buyer.class)
                .setParameter("name", nameFragment + "%")
                .getResultStream();
    }

    @Override
    public Stream<Buyer> findByNameContains(String nameFragment) {
        return entityManager
                .createQuery("select o from Buyer o where o.name Like :name", Buyer.class)
                .setParameter("name", "%" + nameFragment + "%")
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
}
