package yang.yu.tmall.domain.buyers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Buyers {

    <T extends Buyer> T save(T buyer);

    void delete(Buyer buyer);

    List<Buyer> findAll();

    void deleteAll();

    Optional<Buyer> getById(int id);

    Optional<Buyer> getByName(String name);

    Stream<Buyer> findByNameStartsWith(String nameFragment);

    Stream<Buyer> findByNameContains(String nameFragment);

    Optional<PersonalBuyer> findPersonalBuyerByQQ(String qq);

}
