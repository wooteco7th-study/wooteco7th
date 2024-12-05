package vendingmachine.view;

import java.util.Map;
import vendingmachine.domain.Coin;

public interface OutputView {

    void printAskVendingMachineMoney();

    void printVendingMachineCoins(final Map<Coin, Integer> coins);

    void printAskProducts();

    void printAskInputMoney();

    void printInputMoney(final int money);

    void printAskPurchaseProduct();

    void printRemainingCoins(final Map<Coin, Integer> coins);
}
