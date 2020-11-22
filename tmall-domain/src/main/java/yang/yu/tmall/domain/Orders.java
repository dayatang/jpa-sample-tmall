package yang.yu.tmall.domain;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

public interface Orders {

    Optional<Order> getById(int id);

    Optional<Order> getByOrderNo(String orderNo);

    Stream<Order> findByBuyer(Buyer buyer);

    Stream<Order> findByCreatedBetween(LocalDateTime from, LocalDateTime to);

    Stream<Order> findByBuyerAndCreatedBetween(Buyer buyer, LocalDateTime from, LocalDateTime to);

    Stream<Order> findByBuyerAndStatus(Buyer buyer, OrderStatus status);

    Stream<Order> findByProduct(Product product);
}
