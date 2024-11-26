package vendingmachine.service;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.machine.VendingMachine;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;
import vendingmachine.view.ProductRegistrationRequest;

public class VendingMachineService {
    private final VendingMachine vendingMachine;

    public VendingMachineService(Money initialAmount) {
        this.vendingMachine = new VendingMachine(initialAmount);
    }

    public void addProducts(final List<ProductRegistrationRequest> productRegistrationRequests) {
        for (ProductRegistrationRequest request : productRegistrationRequests) {
            vendingMachine.addProduct(request.toProduct());
        }
    }

    public Map<Coin, Integer> getCoinStatus() {
        return vendingMachine.getCoinStatus();

    }

}
