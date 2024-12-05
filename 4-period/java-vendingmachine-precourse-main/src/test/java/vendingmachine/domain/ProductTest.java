package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @Test
    @DisplayName("상품 금액이 100원 미만이므로 예외가 발생한다.")
    void validateRangeTest() throws Exception {

        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new Product("콜라", 60, 1));
    }

    @Test
    @DisplayName("상품 금액이 10원 단위가 아니므로 예외가 발생한다.")
    void validateUnitTest() throws Exception {

        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new Product("콜라", 101, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("상품 수량이 1 보다 작으므로 예외가 발생한다.")
    void validateQuantityTest(final int quantity) throws Exception {

        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new Product("콜라", 101, quantity));
    }

}
