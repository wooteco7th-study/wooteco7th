package vendingmachine.role;

import vendingmachine.Money;
import vendingmachine.domain.product.Product;

/*
- [ ] 상품명,가격,수량을 입력하여 새로운 상품을 추가할 수 있다.
    - 상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.
- [ ] 상품명으로 상품을 찾아서 상품을 반환할 수 있다
    - 못 찾으면 (throw Exception)
- [ ] 상품을 업데이트 한다.
- [ ] 주어진 금액으로 구매 가능한 상품이 있는지 확인한다.
 */
public interface ProductManager {
    void addProduct(Product product);

    Product findProduct(String productName);

    void updateProduct(Product product);

    boolean canPurchaseAnyProduct(Money money);
}
