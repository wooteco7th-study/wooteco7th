package vendingmachine.role.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import vendingmachine.domain.money.Money;
import vendingmachine.domain.product.Product;
import vendingmachine.exception.repository.DuplicateProductException;
import vendingmachine.exception.repository.ProductNotFoundException;
import vendingmachine.role.ProductManager;

/*
- [ ] 상품명,가격,수량을 입력하여 새로운 상품을 추가할 수 있다.
    - 상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.
- [ ] 상품명으로 상품을 찾아서 상품을 반환할 수 있다
    - 못 찾으면 (throw Exception)
- [ ] 상품을 업데이트 한다.
- [ ] 주어진 금액으로 구매 가능한 상품이 있는지 확인한다.
 */

public class DefaultProductManager implements ProductManager {
    private final Map<String, Product> products = new HashMap<>();

    @Override
    public void addProduct(final Product product) {
        validateNewProduct(product);
        products.put(product.getName(), product);
    }

    @Override
    public Product findProduct(final String productName) {
        return Optional.ofNullable(products.get(productName))
                .orElseThrow(() -> new ProductNotFoundException(productName));
    }

    @Override
    public void updateProduct(final Product product) {
        if (!products.containsKey(product.getName())) {
            throw new ProductNotFoundException(product.getName());
        }
        products.put(product.getName(), product);
    }

    @Override
    public boolean canPurchaseAnyProduct(final Money money) {
        return products.values().stream()
                .filter(Product::hasStock)
                .map(Product::getPrice)
                .anyMatch(price -> !money.isLessThan(price));
    }

    private void validateNewProduct(Product product) {
        if (products.containsKey(product.getName())) {
            throw new DuplicateProductException(product.getName());
        }
    }
}
