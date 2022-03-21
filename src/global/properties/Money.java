package global.properties;

import java.math.BigInteger;

public class Money {

    private final BigInteger amount;

    public Money(final BigInteger amount) {
        this.amount = amount;
    }

    public static Money of(final long value) {
        return new Money(BigInteger.valueOf(value));
    }

    @Override
    public String toString() {
        return amount.toString() + "원";
    }

    public Money add(final Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money minus(final Money money) {
        if (this.amount.compareTo(money.amount) < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        return new Money(this.amount.subtract(money.amount));
    }

}
