package yang.yu.tmall.repository;

import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.products.Product;

import java.util.stream.Stream;

public interface OrderOperations {
    Stream<Order> findByProduct(Product product);
}
