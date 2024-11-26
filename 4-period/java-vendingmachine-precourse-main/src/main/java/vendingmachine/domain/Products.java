package vendingmachine.domain;

import java.util.HashMap;
import java.util.List;

public class Products {

    private HashMap<String, Product> products;

    public Products(List<Product> products) {
        this.products = create(products);
    }

    private HashMap<String, Product> create(List<Product> products) {
        HashMap<String, Product> productHashMap = new HashMap<>();
        for (Product product : products) {
            productHashMap.put(product.getName(), product);
        }
        return productHashMap;
    }

    public Product find(String productName) {
        return products.get(productName);
    }

    public boolean isProductsEmpty() {
        int maxCount = 0;
        for (String name : products.keySet()) {
            maxCount = Math.max(products.get(name).getCount(), maxCount);
        }
        return maxCount == 0;
    }

    public boolean isMoreThanMinPrice(int money) {
        for (String name : products.keySet()) {
            if (money >= products.get(name).getPrice()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : products.keySet()) {
            stringBuilder.append(products.get(name).toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
