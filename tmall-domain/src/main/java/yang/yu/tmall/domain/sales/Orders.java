package yang.yu.tmall.domain.sales;

import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.buyers.Buyer;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

public interface Orders {

    Optional<Order> getById(int id);

    Optional<Order> getByOrderNo(String orderNo);

    Stream<Order> findByBuyer(Buyer buyer);

    Stream<Order> findByProduct(Product product);

    /**
     * 寻找在一段时间内的、包含特定商品的订单
     * @param product 商品
     * @param from 起始时间
     * @param until 结束时间（不包含）
     * @return 包含特定商品的订单列表
     */
    Stream<Order> findByProduct(Product product, LocalDateTime from, LocalDateTime until);

    Stream<Order> findByOrgBuyers();

    Order save(Order order);

    void delete(Order order);

}
