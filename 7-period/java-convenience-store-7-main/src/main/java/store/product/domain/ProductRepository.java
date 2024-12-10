package store.product.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import store.error.AppException;
import store.error.ErrorMessage;
import store.promotion.Promotion;
import store.promotion.PromotionRepository;
import store.util.FileReader;
import store.util.StringParser;

public class ProductRepository {

    private static final String PATH = "products.md";

    private final PromotionRepository promotionRepository;
    private final List<Product> products;

    public ProductRepository(final PromotionRepository promotionRepository, final List<Product> products) {
        this.promotionRepository = promotionRepository;
        this.products = products;
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product findByProductName(final String name) {
        return products.stream()
                .filter(product -> Objects.equals(product.getName(), name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_INPUT));
    }

    public int countQuantityByProductName(final String name) {
        return products.stream()
                .filter(product -> Objects.equals(product.getName(), name))
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public boolean existByProductName(final String name) {
        return products.stream()
                .anyMatch(product -> Objects.equals(product.getName() , name));
    }

    public void initialize() {
        final Queue<String> fileLines = FileReader.readFileLines(PATH);
        fileLines.poll();
        while (!fileLines.isEmpty()) {
            final String line = fileLines.poll();
            products.add(createProduct(line));
        }
    }

    private Product createProduct(final String line) {
        final String[] splitLine = line.split(",");
        final String name = splitLine[0];
        final int price = StringParser.parseToInt(splitLine[1], ErrorMessage.INVALID_NUMBER_FORMAT);
        final int quantity = StringParser.parseToInt(splitLine[2], ErrorMessage.INVALID_NUMBER_FORMAT);
        final String promotionName = splitLine[3];
        if (Objects.equals(promotionName, "null")) {
            return new Product(name, ProductType.NON_PROMOTION, quantity, price, "");
        }
        final Promotion promotion = promotionRepository.findByName(promotionName);
        return new PromotionProduct(name, ProductType.PROMOTION, quantity, price, promotion, promotionName);
    }
}
