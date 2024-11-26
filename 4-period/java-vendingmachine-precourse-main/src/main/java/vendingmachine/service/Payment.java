package vendingmachine.service;

import vendingmachine.domain.Drinker;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
import vendingmachine.excpetion.InputException;

public class Payment {

    private final Products products;
    private final Drinker drinker;

    public Payment(Products products, Drinker drinker) {
        this.products = products;
        this.drinker = drinker;
    }

    public void pay(String buy) {
        validateProduct(buy);
        Product product = findProduct(buy);
        validatePrice(product);
        validateCount(product);
        product.setCount();
        drinker.setMoney(product.getPrice());
    }

    private void validateProduct(String name) {
        if (products.isNotContain(name)) {
            throw new InputException();
        }
    }

    private void validatePrice(Product product) {
        if (product.getPrice() > drinker.getMoney()) {
            throw new InputException();
        }
    }

    private void validateCount(Product product) {
        if (product.getCount() <= 0) {
            throw new InputException();
        }
    }

    private Product findProduct(String buy) {
        return products.find(buy);
    }
}
