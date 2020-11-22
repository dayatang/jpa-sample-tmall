package yang.yu.tmall.domain;

import java.util.Optional;
import java.util.stream.Stream;

public interface Orders {

    Optional<Order> getById(int id);

    Optional<Order> getByOrderNo(String orderNo);

    Stream<Order> findByBuyer(Buyer buyer);

    Stream<Order> findByProduct(Product product);

    Order save(Order order);

    void delete(Order order);

}
