package user;

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

}
