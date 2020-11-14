package yang.yu.tmall.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
public class Money {

    public static final Money ZERO = Money.valueOf(0);

    private BigDecimal value = BigDecimal.ZERO;

    public static Money valueOf(BigDecimal amount) {
        return new Money(amount);
    }

    public static Money valueOf(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money valueOf(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money valueOf(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money() {
    }

    public Money(BigDecimal amount) {
        if (amount == null) {
            this.value = BigDecimal.ZERO;
            return;
        }
        this.value = amount;
    }

    public BigDecimal getValue() {
        return value;
    }

    private void setValue(BigDecimal value) {
        this.value = value;
    }

    public Money add(Money amount) {
        return new Money(this.value.add(amount.value));
    }

    public Money subtract(Money amount) {
        return new Money(this.value.subtract(amount.value));
    }

    public Money multiply(int amount) {
        return new Money(this.value.multiply(new BigDecimal(amount)));
    }

    public Money multiply(long amount) {
        return new Money(this.value.multiply(new BigDecimal(amount)));
    }

    public Money multiply(BigDecimal amount) {
        if (amount == null) {
            return Money.ZERO;
        }
        return new Money(this.value.multiply(amount));
    }

    public Money multiply(double amount) {
        return new Money(this.value.multiply(new BigDecimal(amount)));
    }

    public Money divide(int amount) {
        return new Money(this.value.divide(new BigDecimal(amount)));
    }

    public Money divide(long amount) {
        return new Money(this.value.divide(new BigDecimal(amount)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money) o;
        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.setScale(2, RoundingMode.HALF_UP).toString();
    }
}
