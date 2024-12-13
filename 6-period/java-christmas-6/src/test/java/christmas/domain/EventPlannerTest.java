package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.discount.Badge;
import christmas.domain.discount.Discount;
import java.util.List;
import org.junit.jupiter.api.Test;

class EventPlannerTest {

    @Test
    void getCanReceiveDiscountTest() {
        //given
        final List<String> orderMenu = List.of("티본스테이크-3", "초코케이크-1");
        final VisitDate visitDate = VisitDate.of(3);
        final OrderMenuGroup orderMenuGroup = OrderMenuGroup.of(orderMenu);
        //when
        final EventPlanner eventPlanner = EventPlanner.of(visitDate, orderMenuGroup);
        final List<Discount> canReceiveDiscount = eventPlanner.getCanReceiveDiscount();
        //then
        assertThat(canReceiveDiscount).hasSize(4);
    }

    @Test
    void getBenefitMenu() {
        //given
        final List<String> orderMenu = List.of("티본스테이크-3", "초코케이크-1");
        final VisitDate visitDate = VisitDate.of(3);
        final OrderMenuGroup orderMenuGroup = OrderMenuGroup.of(orderMenu);
        //when
        final EventPlanner eventPlanner = EventPlanner.of(visitDate, orderMenuGroup);
        final Menu benefitMenu = eventPlanner.getBenefitMenu();
        final int benefitQuantity = eventPlanner.getBenefitQuantity();

        //then
        assertAll(
                () -> assertThat(benefitMenu).isEqualTo(Menu.CHAMPAGNE),
                () -> assertThat(benefitQuantity).isEqualTo(1)
        );
    }


    @Test
    void getBadge() {
        //given
        final List<String> orderMenu = List.of("티본스테이크-3", "초코케이크-1");
        final VisitDate visitDate = VisitDate.of(3);
        final OrderMenuGroup orderMenuGroup = OrderMenuGroup.of(orderMenu);
        //when
        final EventPlanner eventPlanner = EventPlanner.of(visitDate, orderMenuGroup);
        final Badge badge = eventPlanner.getBadge();
        //then
        assertThat(badge).isEqualTo(Badge.SANTA);
    }
}
