package christmas.domain.event.gift;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.Menu;
import christmas.domain.Quantity;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusGiftTest {

    @Test
    @DisplayName("12월 중 12만원 이상 구매시 적용 가능하다.")
    void isApplicable() {
        // Given
        BonusGift bonusGift = createBonusGift(2, Menu.바비큐립, 3);

        // When & Then
        assertThat(bonusGift.isApplicable()).isTrue();
    }

    @Test
    @DisplayName("12월이 아니면 적용 불가능하다.")
    void isApplicable_NotDecember() {
        // Given
        BonusGift bonusGift = new BonusGift(new Day(2023, 11, 2),
                createOrders(Menu.바비큐립, 3));

        // When & Then
        assertThat(bonusGift.isApplicable()).isFalse();
    }

    @Test
    @DisplayName("12만원 미만 구매시 적용 불가능하다.")
    void isApplicable_NotEnoughMoney() {
        // Given
        BonusGift bonusGift = createBonusGift(2, Menu.바비큐립, 2);

        // When & Then
        assertThat(bonusGift.isApplicable()).isFalse();
    }

    @Test
    @DisplayName("증정품 가격을 계산한다.")
    void calculateAmount() {
        // Given
        BonusGift bonusGift = createBonusGift(2, Menu.바비큐립, 3);

        // When & Then
        assertThat(bonusGift.calculateAmount()).isEqualTo(new BigDecimal(25_000));
    }

    @Test
    @DisplayName("증정품을 반환한다.")
    void provideGiftItems() {
        // Given
        BonusGift bonusGift = createBonusGift(2, Menu.바비큐립, 3);

        // When
        Map<Menu, Quantity> items = bonusGift.provideGiftItems();

        // Then
        assertThat(items)
                .hasSize(1)
                .containsEntry(Menu.샴페인, new Quantity(1));
    }

    @Test
    @DisplayName("증정품이 없을 경우 빈 map을 반환한다.")
    void provideGiftItems_empty() {
        // Given
        BonusGift bonusGift = createBonusGift(2, Menu.바비큐립, 1);

        // When
        Map<Menu, Quantity> items = bonusGift.provideGiftItems();

        // Then
        assertThat(items).isEmpty();
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void getName() {
        // Given
        BonusGift bonusGift = createBonusGift(2, Menu.바비큐립, 3);

        // When & Then
        assertThat(bonusGift.getName()).isEqualTo("증정 이벤트");
    }

    private BonusGift createBonusGift(final int day, final Menu menu, final int value) {
        return new BonusGift(new Day(2023, 12, day),
                createOrders(menu, value));
    }

    private Orders createOrders(final Menu menu, final int value) {
        return new Orders(List.of(new Order(menu, new Quantity(value))));
    }
}
