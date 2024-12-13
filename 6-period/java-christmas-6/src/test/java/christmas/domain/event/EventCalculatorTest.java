package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.domain.date.Day;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("이벤트 계산기 테스트")
class EventCalculatorTest {

    @Test
    @DisplayName("총 주문 금액을 구한다.")
    void calculateTotalOrderPrice() {
        // Given
        Day day = new Day(5);
        Orders orders = new Orders(List.of(new Order(Menu.바비큐립, 1), new Order(Menu.샴페인, 1)));
        EventCalculator eventCalculator = new EventCalculator(day, orders);

        // When & Then
        assertThat(eventCalculator.calculateTotalOrderPrice()).isEqualTo(79_000);
    }

    @Test
    @DisplayName("증정품을 구한다.")
    void 증정품을_구한다() {
        // Given
        Day day = new Day(5);
        Orders orders = new Orders(List.of(new Order(Menu.바비큐립, 3), new Order(Menu.샴페인, 1)));
        EventCalculator eventCalculator = new EventCalculator(day, orders);

        // When
        Map<Menu, Integer> gifts = eventCalculator.calculateGift();
        // & Then
        assertAll(
                () -> assertThat(gifts).hasSize(1),
                () -> assertThat(gifts).containsEntry(Menu.샴페인, 1)
        );
    }

    @Test
    @DisplayName("최소 조건을 만족하지 못할 경우 증정품을 얻지 못한다.")
    void 최소_조건을_만족하지_못할_경우_증정품을_얻지_못한다() {
        // Given
        Day day = new Day(5);
        Orders orders = new Orders(List.of(new Order(Menu.바비큐립, 1), new Order(Menu.샴페인, 1)));
        EventCalculator eventCalculator = new EventCalculator(day, orders);

        // When
        Map<Menu, Integer> gifts = eventCalculator.calculateGift();
        // Then
        assertThat(gifts).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"1,0", "3,25000"})
    void 증정품_할인금액_계산(int quantity, int discount) {
        // given
        Day day = new Day(5);
        Orders orders = new Orders(List.of(new Order(Menu.바비큐립, quantity), new Order(Menu.샴페인, 1)));
        EventCalculator eventCalculator = new EventCalculator(day, orders);

        // when & then
        assertThat(eventCalculator.calculateGiftDiscount()).isEqualTo(discount);
    }

    @ParameterizedTest
    @CsvSource({"5,1400", "25,3400", "26,0"})
    void 크리스마스_디데이할인_계산(int dayValue, int expected) {
        // Given
        Day day = new Day(dayValue);
        Orders orders = new Orders(List.of(new Order(Menu.바비큐립, 3), new Order(Menu.샴페인, 1)));
        EventCalculator eventCalculator = new EventCalculator(day, orders);

        // When & Then
        assertThat(eventCalculator.calculateChristmasDdayDiscount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1,4046", "3,2023", "26,2023"})
    void 요일_할인_계산(int dayValue, int expected) {
        // Given
        Day day = new Day(dayValue);
        Orders orders = new Orders(List.of(new Order(Menu.바비큐립, 2), new Order(Menu.초코케이크, 1)));
        EventCalculator eventCalculator = new EventCalculator(day, orders);

        // When & Then
        assertThat(eventCalculator.calculateDayDiscount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1,0", "3,1000", "25,1000"})
    void 특별_할인_계산(int dayValue, int expected) {
        // Given
        Day day = new Day(dayValue);
        Orders orders = new Orders(List.of(new Order(Menu.바비큐립, 2), new Order(Menu.초코케이크, 1)));
        EventCalculator eventCalculator = new EventCalculator(day, orders);

        // When & Then
        assertThat(eventCalculator.calculateSpecialDiscount()).isEqualTo(expected);
    }
}
