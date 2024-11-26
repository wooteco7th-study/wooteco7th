package vendingmachine.domain.machine;

import java.util.Map;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;
import vendingmachine.domain.product.Product;
import vendingmachine.exception.domain.InvalidInitialAmountException;
import vendingmachine.role.ProductManager;
import vendingmachine.role.impl.DefaultProductManager;
import vendingmachine.role.impl.RandomCoinGenerator;

public class VendingMachine {
    private final ProductManager productManager;
    private final CoinInventory coinInventory;
    private final Money money;

    public VendingMachine(Money initialAmount) {
        validateInitialAmount(initialAmount);
        this.coinInventory = initializeCoinInventory(initialAmount);
        this.productManager = new DefaultProductManager();
        this.money = initialAmount;
    }

    private CoinInventory initializeCoinInventory(Money initialAmount) {
        return CoinInventory.from(new RandomCoinGenerator().generate(initialAmount));
    }


    public Map<Coin, Integer> getCoinStatus() {
        return coinInventory.getCoins();
    }

    public void addProduct(Product product) {
        productManager.addProduct(product);
    }

    private void validateInitialAmount(Money amount) {
        if (amount.isLessThan(Money.zero()) || amount.isNotDivisibleBy(10)) {
            throw new InvalidInitialAmountException();
        }
    }
}
