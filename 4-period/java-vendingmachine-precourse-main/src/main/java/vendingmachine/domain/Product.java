package vendingmachine.domain;

public class Product {

    private final String name;
    private final int price;
    private final int count;

    public Product(String name, int price, int count) {
        validatePrice(price);
        this.name = name;
        this.price = price;
        this.count = count;
    }

    private void validatePrice(int price) {
        if (price < 100) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return name + " " + price + " " + count;
    }
}
