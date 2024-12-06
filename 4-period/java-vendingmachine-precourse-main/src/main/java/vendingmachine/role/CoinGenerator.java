package vendingmachine.role;

import java.util.Map;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;

/*

- [ ] 자판기가 보유하고 있는 금액으로 동전을 무작위로 생성한다.
 */
public interface CoinGenerator {
    Map<Coin, Integer> generate(Money amount);

}
