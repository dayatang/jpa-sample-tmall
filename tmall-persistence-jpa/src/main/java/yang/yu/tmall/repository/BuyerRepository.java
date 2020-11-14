package yang.yu.tmall.repository;

import yang.yu.tmall.domain.Buyer;
import yang.yu.tmall.domain.Buyers;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class BuyerRepository implements Buyers {

    private final EntityManager entityManager;

    public BuyerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Buyer save(Buyer buyer) {
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
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Buyer").executeUpdate();
    }

    @Override
    public Optional<Buyer> findById(int id) {
        return Optional.ofNullable(entityManager.find(Buyer.class, id));
    }

    @Override
    public Optional<Buyer> findByName(String name) {
        Buyer buyer = (Buyer) entityManager
                .createQuery("select o from Buyer o where o.name = :name")
                .setParameter("name", name)
                .getSingleResult();
        return Optional.ofNullable(buyer);
    }

    @Override
    public Stream<Buyer> findByNameStartsWith(String nameFragment) {
        return entityManager
                .createQuery("select o from Buyer o where o.name Like :name")
                .setParameter("name", nameFragment + "%")
                .getResultList()
                .stream();
    }

    @Override
    public Stream<Buyer> findByNameContains(String nameFragment) {
        return entityManager
                .createQuery("select o from Buyer o where o.name Like :name")
                .setParameter("name", "%" + nameFragment + "%")
                .getResultList()
                .stream();
    }
}
