package vendingmachine.domain;

import vendingmachine.excpetion.InputException;

public class Product {

    private final String name;
    private final int price;
    private int count;

    public Product(String name, int price, int count) {
        validatePrice(price);
        this.name = name;
        this.price = price;
        this.count = count;
    }

    private void validatePrice(int price) {
        if (price < 100) {
            throw new InputException();
        }
    }

    public void setCount() {
        this.count -= 1;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " " + price + " " + count;
    }
}
