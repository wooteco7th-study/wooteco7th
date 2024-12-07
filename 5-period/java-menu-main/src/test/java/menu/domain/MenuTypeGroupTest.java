package menu.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeGroupTest {

    @DisplayName("2개를 초과하는 메뉴 타입은 업데이트 하지 않는다.")
    @Test
    void updateMenuTypesTest() {
        //given
        final List<MenuType> menuTypes = new ArrayList<>(List.of(MenuType.KOREAN_FOOD, MenuType.KOREAN_FOOD, MenuType.AMERICAN_STYLE,
                MenuType.AMERICAN_STYLE,
                MenuType.CHINESE_FOOD, MenuType.CHINESE_FOOD, MenuType.JAPANESE_FOOD, MenuType.JAPANESE_FOOD));
        //when
        final MenuTypeGroup menuTypeGroup = new MenuTypeGroup(menuTypes);
        final MenuType menuType = menuTypeGroup.updateMenuTypes();
        //then
        assertThat(menuType).isEqualTo(MenuType.ASIAN_FOOD);
    }
}
