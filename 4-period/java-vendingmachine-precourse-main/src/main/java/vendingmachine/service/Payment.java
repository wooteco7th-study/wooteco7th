package vendingmachine.service;

import vendingmachine.domain.Drinker;
import vendingmachine.domain.Product;
import vendingmachine.domain.Products;

public class Payment {

    private final Products products;
    private final Drinker drinker;

    public Payment(Products products, Drinker drinker) {
        this.products = products;
        this.drinker = drinker;
    }

    public void pay(String buy) {
        Product product = findProduct(buy);
        if (product.getPrice() > drinker.getMoney()) {
            throw new IllegalArgumentException();
        }
        product.setCount();
        drinker.setMoney(product.getPrice());
    }

    private Product findProduct(String buy) {
        return products.find(buy);
    }
}
