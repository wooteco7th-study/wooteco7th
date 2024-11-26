package vendingmachine;

import java.util.Objects;

public class Money {
    private final int amount;

    private Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public static Money zero() {
        return new Money(0);
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0보다 작을 수 없습니다.");
        }
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money minus(Money other) {
        if (this.amount < other.amount) {
            throw new IllegalArgumentException("[ERROR] 금액이 부족합니다.");
        }
        return new Money(this.amount - other.amount);
    }

    public boolean isLessThan(Money other) {
        return this.amount < other.amount;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return this.amount >= other.amount;
    }

    public boolean isNotDivisibleBy(int divisor) {
        return this.amount % divisor != 0;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
