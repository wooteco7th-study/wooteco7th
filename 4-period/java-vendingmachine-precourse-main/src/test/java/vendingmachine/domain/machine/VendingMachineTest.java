package vendingmachine.domain.machine;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;
import vendingmachine.domain.product.Product;

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

    @DisplayName("상품 1개 저장 기능 작동 테스트")
    @Test
    void 상품_1개_정상_저장() {
        // given
        Money inputMoney = Money.of(3000);
        VendingMachine vendingMachine = new VendingMachine(inputMoney);
        Product product = Product.create("콜라", Money.of(3000), 10);

        // when
        Assertions.assertThatCode(() -> vendingMachine.addProduct(product))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복 상품 예외 반환 테스트")
    @Test
    void 중복_상품_저장_예외_반환() {
        // given
        Money inputMoney = Money.of(3000);
        VendingMachine vendingMachine = new VendingMachine(inputMoney);
        Product product1 = Product.create("콜라", Money.of(3000), 10);
        vendingMachine.addProduct(product1);

        // when
        Product product2 = Product.create("콜라", Money.of(3000), 10);

        // then
        Assertions.assertThatThrownBy(() -> vendingMachine.addProduct(product2))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
