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

    private RecommendedMenus getRandomMenu(final RecommendedMenus recommendedMenus,
                                           final List<String> totalMenus) {
        while (true) {
            try {
                Menu randomMenu = Menu.from(menuGenerator.chooseMenu(totalMenus));
                return recommendedMenus.addMenu(randomMenu); // 코치가 먹을 수 있는 메뉴인지 검증
            } catch (IllegalArgumentException ignored) {
                System.out.println(ignored.getMessage());
            }
        }
    }

    public List<CoachRecommendedMenus> makeWeekDayMenus(final List<CoachCannotEatMenus> coachCannotEatMenus) {
        List<RecommendedMenus> menus = initializeRecommendedMenus(coachCannotEatMenus);
        for (int i = 0; i < WEEKDAY_SIZE; i++) {
            recommendPerCoach(coachCannotEatMenus, menus, i);
        }
        return makeCoachRecommendedMenus(coachCannotEatMenus, menus);
    }

    private List<CoachRecommendedMenus> makeCoachRecommendedMenus(final List<CoachCannotEatMenus> coachCannotEatMenus,
                                                                 final List<RecommendedMenus> menus) {
        List<CoachRecommendedMenus> coachRecommendedMenus = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            coachRecommendedMenus.add(new CoachRecommendedMenus(coachCannotEatMenus.get(i), menus.get(i)));
        }
        return coachRecommendedMenus;
    }

    private void recommendPerCoach(final List<CoachCannotEatMenus> coachCannotEatMenus, final List<RecommendedMenus> menus,
                           final int i) {
        Category category = categories.getCategory(i);
        for (int j = 0; j < coachCannotEatMenus.size(); j++) {
            RecommendedMenus recommendedMenus = menus.get(j);
            List<String> totalMenuNames = Menu.findMenuNames(category);
            getRandomMenu(recommendedMenus, totalMenuNames);
        }
    }

    private List<RecommendedMenus> initializeRecommendedMenus(final List<CoachCannotEatMenus> coachCannotEatMenus) {
        List<RecommendedMenus> menus = new ArrayList<>();
        for (int i = 0; i < coachCannotEatMenus.size(); i++) {
            menus.add(new RecommendedMenus(coachCannotEatMenus.get(i), new ArrayList<>()));
        }
        return menus;
    }
}
