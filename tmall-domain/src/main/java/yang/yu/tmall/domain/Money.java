package yang.yu.tmall.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Money {

    public static final Money ZERO = Money.valueOf(0);

    private BigDecimal amount = BigDecimal.ZERO;

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
            this.amount = BigDecimal.ZERO;
            return;
        }
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money subtract(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money multiply(int amount) {
        return new Money(this.amount.multiply(new BigDecimal(amount)));
    }

    public Money multiply(long amount) {
        return new Money(this.amount.multiply(new BigDecimal(amount)));
    }

    public Money multiply(BigDecimal amount) {
        if (amount == null) {
            return Money.ZERO;
        }
        return new Money(this.amount.multiply(amount));
    }

    public Money multiply(double amount) {
        return new Money(this.amount.multiply(new BigDecimal(amount)));
    }

    public Money divide(int amount) {
        return new Money(this.amount.divide(new BigDecimal(amount)));
    }

    public Money divide(long amount) {
        return new Money(this.amount.divide(new BigDecimal(amount)));
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
        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
