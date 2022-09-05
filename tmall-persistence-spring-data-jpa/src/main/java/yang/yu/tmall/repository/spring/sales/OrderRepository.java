package yang.yu.tmall.repository.spring.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.catalogue.Product;
import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.sales.Orders;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 订单仓储的实现
 */
@Named
public interface OrderRepository extends Orders, JpaRepository<Order, Integer> {

    /**
     * 根据ID获取订单
     * @param id 订单ID
     * @return 订单
     */
    Optional<Order> getById(int id);

    /**
     * 根据订单编号获取订单
     * @param orderNo 订单编号
     * @return 订单
     */
    Optional<Order> getByOrderNo(String orderNo);

    default Stream<Order> findByBuyer(Buyer buyer) {
        return findByBuyerOrderByCreatedDesc(buyer);
    }

    Stream<Order> findByBuyerOrderByCreatedDesc(Buyer buyer);

    @Override
    @Query("select o.order from OrderLine o where o.product = :product order by o.order.created desc")
    Stream<Order> findByProduct(@Param("product") Product product);

    @Query("select o.order from OrderLine o where o.product = :product and o.created >= :fromTime" +
            " and o.created < :untilTime order by o.order.created desc")
    Stream<Order> findByProduct(@Param("product") Product product,
                                @Param("fromTime") LocalDateTime from,
                                @Param("untilTime") LocalDateTime until);

    @Override
    @Query("select o from Order o join o.buyer b where TYPE(b) = OrgBuyer order by o.created desc")
    Stream<Order> findByOrgBuyers();


}
