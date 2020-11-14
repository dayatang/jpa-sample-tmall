package yang.yu.tmall.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MoneyTest extends BaseUnitTest {

    @Test
    void valueOfBigDecimal() {
        assertThat(Money.valueOf(new BigDecimal("15.554"))).isEqualTo(Money.valueOf("15.55"));
        assertThat(Money.valueOf(new BigDecimal("15.555"))).isEqualTo(Money.valueOf("15.56"));
    }

    @Test
    void valueOfInt() {
        int value = 10;
        assertThat(Money.valueOf(value)).isEqualTo(Money.valueOf("10.00"));
    }

    @Test
    void valueOfLong() {
        long value = 10000000L;
        assertThat(Money.valueOf(value)).isEqualTo(Money.valueOf("10000000.00"));
    }

    @Test
    void valueOfDouble() {
        double value = 10.556;
        assertThat(Money.valueOf(value)).isEqualTo(Money.valueOf("10.56"));
    }

    @Test
    void add() {
        Money money1 = Money.valueOf(67);
        Money money2 = Money.valueOf(46);
        assertThat(money1.add(money2)).isEqualTo(Money.valueOf("113.00"));
    }

    @Test
    void subtract() {
        Money money1 = Money.valueOf(67);
        Money money2 = Money.valueOf(46);
        assertThat(money1.subtract(money2)).isEqualTo(Money.valueOf("21.00"));
    }

    @Test
    void multiplyByInt() {
        assertThat(Money.valueOf(67).multiply(2)).isEqualTo(Money.valueOf("134.00"));
    }

    @Test
    void multiplyByLong() {
        assertThat(Money.valueOf(67).multiply(2L)).isEqualTo(Money.valueOf("134.00"));
    }

    @Test
    void multiplyByDouble() {
        Money money = Money.valueOf(6.79);
        assertThat(money.multiply(2.5)).isEqualTo(Money.valueOf("16.98"));
    }

    @Test
    void multiplyByBigDecimal() {
        Money money = Money.valueOf(67);
        BigDecimal m = new BigDecimal(3);
        assertThat(money.multiply(m)).isEqualTo(Money.valueOf("201.00"));
    }

    @Test
    void dividedByInt() {
        Money money = Money.valueOf(68);
        assertThat(money.divide(2)).isEqualTo(Money.valueOf("34.00"));
    }

    @Test
    void dividedByLong() {
        Money money = Money.valueOf(68);
        assertThat(money.divide(2L)).isEqualTo(Money.valueOf("34.00"));
    }

    @Test
    void testToString() {
        assertThat(Money.valueOf(15.554).toString()).isEqualTo("15.55");
        assertThat(Money.valueOf(15.555).toString()).isEqualTo("15.56");
    }
}