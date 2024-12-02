package vendingmachine.service.creator;

import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.product.Product;
import vendingmachine.domain.product.Products;
import vendingmachine.excpetion.InputException;
import vendingmachine.util.Convertor;
import vendingmachine.util.Separator;

public class ProductsCreator {

    private final List<String> products;

    public ProductsCreator(String input) {
        List<String> inputs = Separator.split(input, ";", 0);
        inputs.forEach(this::validate);
        this.products = makeProducts(inputs);
    }

    private void validate(String product) {
        if (product.indexOf("[") != 0 || product.indexOf("]") != product.length() - 1) {
            throw new InputException();
        }
    }

    private List<String> makeProducts(List<String> inputs) {
        return inputs.stream().map(this::remove).collect(Collectors.toList());
    }

    private String remove(String input) {
        return input.substring(1, input.length() - 1);
    }

    private List<String> splitInfo(String product) {
        return Separator.split(product, ",", 3);
    }

    private Product createProduct(String product) {
        validateProduct(product);
        List<String> splitProduct = splitInfo(product);
        validateInfoSize(splitProduct);
        String name = splitProduct.get(0);
        int price = Convertor.changeType(splitProduct.get(1));
        int count = Convertor.changeType(splitProduct.get(2));
        return new Product(name, price, count);
    }

    private void validateProduct(String product) {
        if (isNotContainSemiColon(product)) {
            throw new InputException();
        }
    }

    private void validateInfoSize(List<String> splitProduct) {
        if (splitProduct.size() != 3) {
            throw new InputException();
        }
    }

    private boolean isNotContainSemiColon(String product) {
        return !product.contains(",");
    }

    public Products create() {
        List<Product> newProducts = products.stream()
                .map(this::createProduct)
                .collect(Collectors.toList());
        return new Products(newProducts);
    }
}
