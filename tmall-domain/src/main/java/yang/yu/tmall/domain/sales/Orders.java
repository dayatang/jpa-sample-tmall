package yang.yu.tmall.domain.sales;

import yang.yu.tmall.domain.catalog.Product;
import yang.yu.tmall.domain.buyers.Buyer;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 订单仓储接口
 */
public interface Orders {

    /**
     * 根据ID获取订单
     * @param id 订单ID
     * @return 订单
     */
    Optional<Order> getById(int id);

    /**
     * 根据订单号获取订单
     * @param orderNo 订单号
     * @return 订单
     */
    Optional<Order> getByOrderNo(String orderNo);

    /**
     * 根据买家查询订单，以下单时间逆序排序
     * @param buyer 买家
     * @return 指定买家所下的全部订单
     */
    Stream<Order> findByBuyer(Buyer buyer);

    /**
     * 根据商品查询订单，以下单时间逆序排序
     * @param product 商品
     * @return 包含指定商品的的全部订单
     */
    Stream<Order> findByProduct(Product product);

    /**
     * 寻找在一段时间内的、包含特定商品的订单，以下单时间逆序排序
     * @param product 商品
     * @param from 起始时间
     * @param until 结束时间（不包含）
     * @return 包含特定商品的订单列表
     */
    Stream<Order> findByProduct(Product product, LocalDateTime from, LocalDateTime until);

    /**
     * 获取机构用户所下的全部订单，以下单时间逆序排序
     * @return 订单流
     */
    Stream<Order> findByOrgBuyers();

    /**
     * 下订单
     * @param order 要保存的订单
     * @return 订单
     */
    Order save(Order order);

    /**
     * 删除订单
     * @param order 要删除的订单
     */
    void delete(Order order);

}
