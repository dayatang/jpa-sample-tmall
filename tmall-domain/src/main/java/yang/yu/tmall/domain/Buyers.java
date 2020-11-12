package yang.yu.tmall.domain;

import java.util.List;

public interface Buyers {

    Buyer save(Buyer buyer);

    void remove(Buyer buyer);

    Buyer getBuyId(int id);

    Buyer getByName(String name);

    List<Buyer> findByNameContains(String nameFragment);
}
