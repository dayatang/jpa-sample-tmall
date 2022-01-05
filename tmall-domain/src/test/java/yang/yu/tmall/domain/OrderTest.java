package yang.yu.tmall.domain;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import yang.yu.tmall.domain.commons.Money;
import yang.yu.tmall.domain.products.Product;
import yang.yu.tmall.domain.products.ProductCategory;
import yang.yu.tmall.domain.sales.Order;
import yang.yu.tmall.domain.sales.OrderLine;

import java.math.BigDecimal;
import java.util.Arrays;

class OrderTest extends BaseUnitTest {

    @Test
    void calculateTotalPrice() {
        ProductCategory category = new ProductCategory("foods");

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setProduct(new Product("a", category));
        orderLine1.setUnitPrice(Money.valueOf(150));
        orderLine1.setQuantity(new BigDecimal(3));
        orderLine1.setDiscountRate(new BigDecimal(30));

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setProduct(new Product("b", category));
        orderLine2.setUnitPrice(Money.valueOf(25.4));
        orderLine2.setQuantity(new BigDecimal(3.2));
        orderLine2.setDiscountRate(new BigDecimal(0));

        Order order = new Order("1111", Arrays.asList(orderLine1, orderLine2), null, null);
        double subtotal1 = 150 * 3 * (100 - 30) / 100;
        //System.out.println(subtotal1);
        double subtotal2 = 25.4 * 3.2;
        //System.out.println(subtotal2);
        assertThat(order.getTotalPrice().getValue().doubleValue())
                .isCloseTo(subtotal1 + subtotal2, Percentage.withPercentage(0.00001));
    }
}