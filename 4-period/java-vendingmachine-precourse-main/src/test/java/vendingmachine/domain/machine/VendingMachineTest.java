package vendingmachine.domain.machine;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;

class VendingMachineTest {

    @DisplayName("초기화 금액 입력시, 랜덤 코인 생성")
    @Test
    void 코인_초기화_기능_성공() {
        // given
        Money inputMoney = Money.of(3000);
        VendingMachine vendingMachine = new VendingMachine(inputMoney);

        // when
        Map<Coin, Integer> coinStatus = vendingMachine.getCoinStatus();

        // then
        System.out.println(coinStatus);
    }

}
