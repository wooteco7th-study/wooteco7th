package vendingmachine.domain.machine;

import java.util.Map;
import vendingmachine.Coin;
import vendingmachine.Money;
import vendingmachine.domain.product.Product;
import vendingmachine.role.ProductManager;
import vendingmachine.role.impl.DefaultProductManager;

public class VendingMachine {
    private final ProductManager productManager;
    private final Money money;

    public VendingMachine(Money money) {
        this.productManager = new DefaultProductManager();
        this.money = money;
    }

    public Map<Coin, Integer> getCoinStatus() {
        return null;
    }

    public void addProduct(Product product) {
        productManager.addProduct(product);
    }

}
