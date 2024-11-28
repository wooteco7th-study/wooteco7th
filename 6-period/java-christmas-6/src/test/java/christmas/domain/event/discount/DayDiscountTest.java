package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.Menu;
import christmas.domain.Quantity;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DayDiscountTest {

    @DisplayName("할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"2,4046", "3,2023"})
    void calculateAmount(int day, int expected) {
        // Given
        DayDiscount discount = createDiscount(day, Menu.바비큐립, 2, Menu.아이스크림, 1);

        // When
        BigDecimal amount = discount.calculateAmount();

        // Then
        assertThat(amount).isEqualTo(new BigDecimal(expected));
    }

    @DisplayName("12월이 아니면 0원이다.")
    @Test
    void calculateAmountChristmas() {
        // Given
        DayDiscount discount = new DayDiscount(new Day(2023, 11, 2),
                createOrders(Menu.바비큐립, 2, Menu.레드와인, 2));

        // When
        BigDecimal amount = discount.calculateAmount();

        // Then
        assertThat(amount).isEqualTo(new BigDecimal(0));
    }

    @DisplayName("최소금액을 만족하지 못하면 할인받을 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3})
    void calculateAmountUnderAmount() {
        // Given
        DayDiscount discount = createDiscount(2, Menu.타파스, 1, Menu.아이스크림, 1);

        // When
        BigDecimal amount = discount.calculateAmount();

        // Then
        assertThat(amount).isEqualTo(new BigDecimal(0));
    }

    @ParameterizedTest
    @CsvSource({"2,주말 할인", "3,평일 할인"})
    @DisplayName("이름을 반환한다.")
    void getName(int day, String expected) {
        // Given
        DayDiscount discount = createDiscount(day, Menu.타파스, 2, Menu.아이스크림, 1);

        // When & Then
        assertThat(discount.getName()).isEqualTo(expected);
    }

    private DayDiscount createDiscount(final int day, final Menu menu1, final int value1, final Menu menu2,
                                       final int value2) {
        return new DayDiscount(new Day(2023, 12, day),
                createOrders(menu1, value1, menu2, value2));
    }

    private Orders createOrders(final Menu menu1, final int value1, final Menu menu2, final int value2) {
        return new Orders(List.of(new Order(menu1, new Quantity(value1)),
                new Order(menu2, new Quantity(value2))));
    }
}
