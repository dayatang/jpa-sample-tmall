package yang.yu.tmall.repository.spring.sales;

import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.products.Product;

import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * 订单仓储的特殊操作接口
 */
public interface OrderOperations {

    /**
     * 寻找包含特定产品的订单
     * @param product 产品
     * @return 包含特定产品的订单列表
     */
    Stream<Order> findByProduct(Product product);

    /**
     * 寻找在一段时间内的、包含特定产品的订单
     * @param product 产品
     * @param from 起始时间
     * @param until 结束时间（不包含）
     * @return 包含特定产品的订单列表
     */
    Stream<Order> findByProduct(Product product, LocalDateTime from, LocalDateTime until);

    /**
     * 查找机构买家的订单
     * @return 买家类型为机构的全部订单
     */
    Stream<Order> findByOrgBuyers();
}
