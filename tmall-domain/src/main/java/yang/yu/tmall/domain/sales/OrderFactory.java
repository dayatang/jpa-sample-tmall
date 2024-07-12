package yang.yu.tmall.domain.sales;

import yang.yu.tmall.domain.buyers.Buyer;
import yang.yu.tmall.domain.commons.Address;
import yang.yu.tmall.domain.pricing.PricingService;
import yang.yu.tmall.domain.catalog.Product;

import jakarta.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单工厂类，创建订单
 */
@Named
public class OrderFactory {

    private PricingService pricingService;

    public OrderFactory(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    /**
     * 创建订单实例
     * @param orderNo 订单编号
     * @param productAndQuantity 商品数量
     * @param buyer 买家
     * @param shippingAddress 发货地址
     * @return 订单对象
     */
    public Order createOrder(String orderNo, Map<Product, BigDecimal> productAndQuantity,
                             Buyer buyer, Address shippingAddress) {
        List<OrderLine> orderLines = productAndQuantity.entrySet().stream()
                .map(each -> new OrderLine(each.getKey(), each.getValue(), pricingService.currentPrice(each.getKey())))
                .collect(Collectors.toList());
        return new Order(orderNo, orderLines, buyer, shippingAddress);

    }
}
