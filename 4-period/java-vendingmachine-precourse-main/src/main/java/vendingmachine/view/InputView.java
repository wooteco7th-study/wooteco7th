package vendingmachine.view;

import java.util.List;

public interface InputView {

    int readMoney();

    List<String> readProducts();

    String purchaseProduct();
}
