package vendingmachine.view;

import java.util.Map;
import vendingmachine.domain.Coin;

public interface OutputView {

    void printAskVendingMachineMoney();

    void printVendingMachineCoins(final Map<Coin, Integer> coins);
}
