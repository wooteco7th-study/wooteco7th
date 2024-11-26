package vendingmachine.role;

import java.util.Map;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;

/*
- 주어진 금액만큼 잔돈을 반환한다
- [ ] 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 (재고)소진된 경우 -> 바로 잔돈을 돌려준다.(애플리케이션 종료)
    - 만약 잔돈을 전부 돌려줄 수 없는 경우(아래참조)
- [ ] 잔돈을 반환할 수 없는 경우 -> 잔돈으로 반환할 수 있는 금액만 반환한다 (나머지 투입 금액을 보여주고, 상품 구매 요청을 받는다)
    - 반환되지 않은 금액은 자판기에 남는다.
    - 예를들어, 1000원이 남는데, 잔돈이 600원이라면, 600원을 표시한다.
 */
public interface ChangeProvider {
    Map<Coin, Integer> provideChange(Money change);
}
