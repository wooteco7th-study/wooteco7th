package vendingmachine.service;

import java.util.List;
import vendingmachine.Money;
import vendingmachine.domain.machine.VendingMachine;
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
}
