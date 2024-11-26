package vendingmachine.role;

import vendingmachine.domain.money.Money;
import vendingmachine.dto.PurchaseResult;

/*

- [ ] 상품을 판매하고 결과를 반환한다.
    - 상품 품절 시 (throw Exception)
    - 잔액 부족 시 (throw Exception)
 */
public interface ProductSeller {
    PurchaseResult sell(String productName, Money insertedMoney);
}
