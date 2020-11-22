package yang.yu.tmall.domain.sales;

import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.buyers.Buyer;

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
