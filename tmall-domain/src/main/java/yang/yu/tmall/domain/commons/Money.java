package yang.yu.tmall.domain.commons;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 金额值对象。只保留两位小数
 */
@Embeddable
public class Money {

    private static final int SCALE = 2;

    public static final Money ZERO = Money.valueOf(0);

    private BigDecimal value = BigDecimal.ZERO;

    /**
     * 从BigDecimal生成金额的工厂方法
     * @param amount 金额数量
     * @return 金额对象
     */
    public static Money valueOf(BigDecimal amount) {
        return new Money(amount);
    }

    /**
     * 从int生成金额的工厂方法
     * @param amount 金额数量
     * @return 金额对象
     */
    public static Money valueOf(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    /**
     * 从long生成金额的工厂方法
     * @param amount 金额数量
     * @return 金额对象
     */
    public static Money valueOf(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    /**
     * 从double生成金额的工厂方法
     * @param amount 金额数量
     * @return 金额对象
     */
    public static Money valueOf(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    /**
     * 从double生成金额的工厂方法
     * @param amount 金额数量
     * @return 金额对象
     */
    public static Object valueOf(String amount) {
        return new Money(new BigDecimal(amount));
    }

    /**
     * 构造函数
     */
    public Money() {
    }

    /**
     * 构造函数。以BigDecimal为参数
     * @param amount 金额值
     */
    public Money(BigDecimal amount) {
        if (amount == null) {
            this.value = BigDecimal.ZERO;
            return;
        }
        this.value = amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    /**
     * 获取金额值，以BigDecimal形式表示
     * @return 金额值
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * 设置金额值
     * @param value 要设置的金额值
     */
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
        return value.toString();
    }
}
