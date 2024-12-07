package menu.domain.recommender;

import java.util.ArrayList;
import java.util.List;
import menu.domain.menu.Categories;
import menu.domain.menu.Category;
import menu.domain.menu.CoachCannotEatMenus;
import menu.domain.menu.CoachRecommendedMenus;
import menu.domain.menu.Menu;
import menu.domain.menu.RecommendedMenus;
import menu.domain.random.MenuGenerator;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

// 카테고리에 따라 메뉴 추천
public class MenuRecommender {

    private static final int WEEKDAY_SIZE = 5;

    private final MenuGenerator menuGenerator;
    private final Categories categories;

    public MenuRecommender(final MenuGenerator menuGenerator, final Categories categories) {
        this.menuGenerator = menuGenerator;
        this.categories = categories;
    }

    // - 요일별 추천 메뉴
    //    - [ ]  코치마다 먹을 수 있는 메뉴를 뽑음
    //    - [ ]  랜덤 순서로 섞은 후, `첫 번째 값`을 사용
    //    - [ ]  `이미 추천한 메뉴`, `먹지 못하는 메뉴`도 포함된 리스트를 전달
    //    - [ ]  추천할 수 없는 메뉴인 경우 `다시 섞은 후` 첫 번째 값을 사용해야 한다.
    //    - [ ]  최소 요일 * 코치수 + 중복시 다시 뽑음
    public CoachRecommendedMenus makeWeekDayMenus(CoachCannotEatMenus cannotEatMenus) {
        RecommendedMenus recommendedMenus = new RecommendedMenus(cannotEatMenus, new ArrayList<>());
        List<String> totalMenuNames = Menu.findAllMenuNames();
        for (int i = 0; i < WEEKDAY_SIZE; i++) {
            recommendedMenus = getRandomMenu(recommendedMenus, categories.getCategory(i), totalMenuNames);
        }
        return new CoachRecommendedMenus(cannotEatMenus, recommendedMenus);
    }

    private RecommendedMenus getRandomMenu(final RecommendedMenus recommendedMenus, final Category category, final List<String> totalMenus) {
        while (true) {
            try {
                Menu randomMenu = menuGenerator.chooseMenu(totalMenus);
                validateSameCategory(category, randomMenu);
                return recommendedMenus.addMenu(randomMenu); // 코치가 먹을 수 있는 메뉴인지 검증
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    private void validateSameCategory(final Category category, final Menu inputMenu) {
        if (!inputMenu.getCategory().equals(category)) {
            throw new CustomIllegalArgumentException(ErrorMessage.NOT_SAME_CATEGORY);
        }
    }
}
