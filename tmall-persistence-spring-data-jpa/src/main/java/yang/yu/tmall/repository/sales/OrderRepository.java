package yang.yu.tmall.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.sales.Orders;

import javax.inject.Named;
import java.util.Optional;
import java.util.stream.Stream;

@Named
public interface OrderRepository extends Orders, JpaRepository<Order, Integer>, OrderOperations {
    Optional<Order> getById(int id);

    Optional<Order> getByOrderNo(String orderNo);

    default Stream<Order> findByBuyer(Buyer buyer) {
        return findByBuyerOrderByCreatedDesc(buyer);
    }

    Stream<Order> findByBuyerOrderByCreatedDesc(Buyer buyer);


}
