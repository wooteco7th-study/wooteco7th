package menu.domain.menu;

import static menu.domain.support.CustomAssert.assertIllegalArgument;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import menu.domain.name.Name;
import menu.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

class CoachTest {

    @Test
    void addRecommendMenu() {
        // Given
        Coach coach = new Coach(new Name("코치"), List.of(Menu.가츠동.getMenuName()), List.of(Menu.규동.getMenuName()));

        // When
        coach.addRecommendMenu(Menu.김밥);

        // Then
        assertThat(coach.getRecommendedMenus().getMenus()).contains(Menu.김밥);
    }

    @Test
    void 싫어하는음식() {
        // Given
        Coach coach = new Coach(new Name("코치"), List.of(Menu.가츠동.getMenuName()), List.of(Menu.규동.getMenuName()));

        // When & Then
        assertIllegalArgument(() -> coach.addRecommendMenu(Menu.가츠동), ErrorMessage.INVALID_MENU_COACH_CANNOT_EAT);
    }

    @Test
    void 중복음식() {
        // Given
        Coach coach = new Coach(new Name("코치"), List.of(Menu.가츠동.getMenuName()), List.of(Menu.규동.getMenuName()));

        // When & Then
        assertIllegalArgument(() -> coach.addRecommendMenu(Menu.규동), ErrorMessage.INVALID_MENU_NAME_DUPLICATED);
    }
}
