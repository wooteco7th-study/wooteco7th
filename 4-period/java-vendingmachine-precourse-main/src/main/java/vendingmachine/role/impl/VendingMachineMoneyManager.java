package vendingmachine.role.impl;

import vendingmachine.domain.money.Money;
import vendingmachine.exception.domain.NotEnoughMoneyException;
import vendingmachine.role.MoneyManager;

public class VendingMachineMoneyManager implements MoneyManager {
    private Money money;

    public VendingMachineMoneyManager() {
        this.money = Money.zero();
    }

    @Override
    public void add(final Money money) {
        this.money = this.money.plus(money);
    }

    @Override
    public Money getMoney() {
        return money;
    }

    @Override
    public Money subtract(final Money amount) {
        if (!hasEnough(amount)) {
            throw new NotEnoughMoneyException();
        }
        Money subtractedAmount = money;
        this.money = this.money.minus(amount);
        return subtractedAmount.minus(this.money);
    }

    @Override
    public boolean hasEnough(final Money amount) {
        return !money.isLessThan(amount);
    }
}
