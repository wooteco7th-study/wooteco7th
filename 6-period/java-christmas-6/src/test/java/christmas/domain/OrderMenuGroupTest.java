package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.common.CustomAssertions;
import christmas.error.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuGroupTest {

    @Test
    @DisplayName("음료만 주문하여 예외가 발생한다.")
    void validateOnlyDrinkTest() {
        //given
        final List<String> orderMenus = List.of("콜라-1", "샴페인-1");
        //should
        CustomAssertions.customAssertThatIllegalArgumentException(() -> OrderMenuGroup.of(orderMenus), ErrorMessage.WRONG_ORDER_FORMAT);

    }

}
