package vendingmachine.service;

import vendingmachine.price.HoldingPrice;

public class VendingService {

    public HoldingPrice createHoldingPrice(long amount) {
        return new HoldingPrice(amount);
    }
}
