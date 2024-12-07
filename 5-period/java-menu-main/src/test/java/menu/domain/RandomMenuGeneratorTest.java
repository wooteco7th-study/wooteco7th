package menu.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomMenuGeneratorTest {

    @DisplayName("코치가 싫어하는 메뉴와 이미 추천한 메뉴는 생성하지 않는다.")
    @Test
    void generateTest() {
        //given
        final List<String> hateMenus = List.of("하이라이스", "라멘");
        final List<String> menus = List.of("규동", "우동", "미소시루", "스시", "가츠동", "오니기리");
        final HateMenu hateMenu = new HateMenu(hateMenus);
        final Coach coach = new Coach(new CoachName("형균"), hateMenu, menus);
        //when
        final String menu = RandomMenuGenerator.generate(coach, MenuType.JAPANESE_FOOD);
        //then
        assertThat(menu).isEqualTo("오코노미야끼");
    }
}
