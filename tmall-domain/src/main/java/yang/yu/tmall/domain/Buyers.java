package yang.yu.tmall.domain;

import java.util.List;
import java.util.Optional;

public interface Buyers {

    Buyer save(Buyer buyer);

    void remove(Buyer buyer);

    Optional<Buyer> findById(int id);

    Optional<Buyer> findByName(String name);

    List<Buyer> findByNameContains(String nameFragment);
}
