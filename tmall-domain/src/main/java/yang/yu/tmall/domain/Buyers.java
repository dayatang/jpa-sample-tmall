package yang.yu.tmall.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Buyers {

    <T extends Buyer> T save(T buyer);

    void delete(Buyer buyer);

    List<Buyer> findAll();

    void deleteAll();

    Optional<Buyer> findById(int id);

    Optional<Buyer> findByName(String name);

    Stream<Buyer> findByNameStartsWith(String nameFragment);

    Stream<Buyer> findByNameContains(String nameFragment);

    Optional<PersonalBuyer> findPersonalBuyerByQQ(String qq);

}
