package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.error.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderGroupTest {

    @Test
    @DisplayName("메뉴가 중복되어 예외가 발생한다.")
    void validateDuplicateTest() throws Exception {
        //given
        final List<Order> orders = List.of(new Order(Menu.BARBECUE_RIBS, 1), new Order(Menu.BARBECUE_RIBS, 1));
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new OrderGroup(orders)).withMessageContaining(
                ErrorMessage.INVALID_WRONG_ORDER_FORMAT.getMessage());

    }

    @Test
    @DisplayName("메뉴의 수량이 20을 초과하여 예외가 발생한다.")
    void validateRangeTest() throws Exception {
        //given
        final List<Order> orders = List.of(new Order(Menu.BARBECUE_RIBS, 15), new Order(Menu.CAESAR_SALAD, 6));
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new OrderGroup(orders)).withMessageContaining(
                ErrorMessage.INVALID_WRONG_ORDER_FORMAT.getMessage());

    }

    @Test
    @DisplayName("음료만 주문하여 예외가 발생한다.")
    void validateOrderOnlyDrinkTest() throws Exception {
        //given
        final List<Order> orders = List.of(new Order(Menu.RED_WINE, 1), new Order(Menu.CHAMPAGNE, 1));
        //should
        assertThatIllegalArgumentException().isThrownBy(() -> new OrderGroup(orders)).withMessageContaining(
                ErrorMessage.INVALID_WRONG_ORDER_FORMAT.getMessage());

    }

}
