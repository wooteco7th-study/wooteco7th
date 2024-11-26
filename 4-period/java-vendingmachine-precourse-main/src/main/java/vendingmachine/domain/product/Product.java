package vendingmachine.domain.product;

import vendingmachine.Money;
import vendingmachine.exception.InvalidProductNameException;
import vendingmachine.exception.InvalidProductPriceException;
import vendingmachine.exception.InvalidProductQuantityException;
import vendingmachine.exception.SoldOutException;

public class Product {
    private final String name;
    private final Money price;
    private final int quantity;

    private Product(String name, Money price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Product create(String name, Money price, int quantity) {
        validateProduct(name, price, quantity);
        return new Product(name, price, quantity);
    }

    public Product decreaseQuantity() {
        if (quantity <= 0) {
            throw new SoldOutException();
        }
        return new Product(name, price, quantity - 1);
    }

    public boolean hasStock() {
        return quantity > 0;
    }


    private static void validateProduct(String name, Money price, int quantity) {
        validateName(name);
        validatePrice(price);
        validateQuantity(quantity);
    }

    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidProductNameException();
        }
    }

    // // 상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.
    private static void validatePrice(Money price) {
        if (price.isLessThan(Money.of(100)) || price.isNotDivisibleBy(10)) {
            throw new InvalidProductPriceException();
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new InvalidProductQuantityException();
        }
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
