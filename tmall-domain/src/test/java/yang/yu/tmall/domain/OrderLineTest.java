package yang.yu.tmall.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class OrderLineTest extends BaseUnitTest {

    @Test
    void calculateSubTotal() {
        OrderLine orderLine = new OrderLine();
        orderLine.setUnitPrice(Money.valueOf(150));
        orderLine.setQuantity(new BigDecimal(3));
        orderLine.setDiscountRate(new BigDecimal(30));
        assertThat(orderLine.getSubTotal()).isEqualTo(Money.valueOf(150 * 3 * (100 - 30) / 100));
    }
}