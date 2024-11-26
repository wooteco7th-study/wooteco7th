package vendingmachine.role;

import vendingmachine.domain.money.Money;

/*

> 투입 금액으로는 동전을 생성하지 않는다.

- [ ] 금액을 추가한다.
- [ ] 주어진 금액만큼의 잔액이 있는지 확인한다.
- [ ] 금액을 차감한다
    - 잔액 부족시 (throw Exception)
- [ ] 현재 잔액을 조회한다.

 */
public interface MoneyManager {
    void add(Money money);

    Money getMoney();

    Money subtract(Money amount);

    boolean hasEnough(Money amount);


}
