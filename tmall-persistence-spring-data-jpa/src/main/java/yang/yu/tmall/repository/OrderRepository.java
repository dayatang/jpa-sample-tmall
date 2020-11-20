package yang.yu.tmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.Buyer;
import yang.yu.tmall.domain.Order;
import yang.yu.tmall.domain.OrderStatus;
import yang.yu.tmall.domain.Orders;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Named
public interface OrderRepository extends Orders, JpaRepository<Order, Integer> {
    Optional<Order> getById(int id);

    Optional<Order> getByOrderNo(String orderNo);

    Stream<Order> findByBuyerOrderByCreatedDesc(Buyer buyer);

    Stream<Order> findByCreatedBetweenOrderByCreatedDesc(LocalDateTime from, LocalDateTime to);

    Stream<Order> findByBuyerAndCreatedBetweenOrderByCreatedDesc(Buyer buyer, LocalDateTime from, LocalDateTime to);

    Stream<Order> findByBuyerAndStatusOrderByCreatedDesc(Buyer buyer, OrderStatus status);

}
