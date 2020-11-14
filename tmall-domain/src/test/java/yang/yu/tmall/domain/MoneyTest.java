package yang.yu.tmall.domain;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MoneyTest extends BaseUnitTest {

    @Test
    void valueOfBigDecimal() {
        BigDecimal value = new BigDecimal(3.55);
        assertThat(Money.valueOf(value).getValue()).isEqualTo(value);
    }

    @Test
    void valueOfInt() {
        int value = 10;
        assertThat(Money.valueOf(value).getValue()).isEqualTo(new BigDecimal(value));
    }

    @Test
    void valueOfLong() {
        long value = 10000000L;
        assertThat(Money.valueOf(value).getValue()).isEqualTo(new BigDecimal(value));
    }

    @Test
    void valueOfDouble() {
        double value = 10.55;
        assertThat(Money.valueOf(value).getValue().doubleValue()).isCloseTo(value, Percentage.withPercentage(0.00001));
    }

    @Test
    void add() {
        Money money1 = Money.valueOf(67);
        Money money2 = Money.valueOf(46);
        assertThat(money1.add(money2)).isEqualTo(Money.valueOf(67 + 46));
    }

    @Test
    void subtract() {
        Money money1 = Money.valueOf(67);
        Money money2 = Money.valueOf(46);
        assertThat(money1.subtract(money2)).isEqualTo(Money.valueOf(67 - 46));
    }

    @Test
    void multiplyByInt() {
        Money money = Money.valueOf(67);
        assertThat(money.multiply(2)).isEqualTo(Money.valueOf(67 * 2));
    }

    @Test
    void multiplyByLong() {
        Money money = Money.valueOf(67);
        assertThat(money.multiply(2L)).isEqualTo(Money.valueOf(67 * 2));
    }

    @Test
    void multiplyByDouble() {
        Money money = Money.valueOf(67);
        assertThat(money.multiply(2.5).getValue().doubleValue())
                .isCloseTo(67 * 2.5, Percentage.withPercentage(0.00001));
    }

    @Test
    void multiplyByBigDecimal() {
        Money money = Money.valueOf(67);
        BigDecimal m = new BigDecimal(3);
        assertThat(money.multiply(m)).isEqualTo(Money.valueOf(67 * 3));
    }

    @Test
    void dividedByInt() {
        Money money = Money.valueOf(68);
        assertThat(money.divide(2)).isEqualTo(Money.valueOf(68 / 2));
    }

    @Test
    void dividedByLong() {
        Money money = Money.valueOf(68);
        assertThat(money.divide(2L)).isEqualTo(Money.valueOf(68 / 2));
    }

    @Test
    void testToString() {
        assertThat(Money.valueOf(15.554).toString()).isEqualTo("15.55");
        assertThat(Money.valueOf(15.555).toString()).isEqualTo("15.56");
    }
}