package yang.yu.tmall.repository;

import yang.yu.tmall.domain.Order;
import yang.yu.tmall.domain.Product;

import java.util.stream.Stream;

public interface OrderOperations {
    Stream<Order> findByProduct(Product product);
}
