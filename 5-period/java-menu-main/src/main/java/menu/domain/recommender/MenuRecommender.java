package menu.domain.recommender;

import java.util.ArrayList;
import java.util.List;
import menu.domain.menu.Category;
import menu.domain.menu.CoachCannotEatMenus;
import menu.domain.menu.Menu;
import menu.domain.menu.RecommendedMenus;
import menu.domain.random.CategoryGenerator;
import menu.domain.random.MenuGenerator;

public class MenuRecommender {

    private static final int WEEKDAY_SIZE = 5;

    private final CategoryGenerator categoryGenerator;
    private final MenuGenerator menuGenerator;
    private final List<CoachCannotEatMenus> coachCannotEatMenus;
    private final List<RecommendedMenus> recommendedMenus;

    public MenuRecommender(final CategoryGenerator categoryGenerator, final MenuGenerator menuGenerator,
                           final List<CoachCannotEatMenus> coachCannotEatMenus) {
        this.categoryGenerator = categoryGenerator;
        this.menuGenerator = menuGenerator;
        this.coachCannotEatMenus = coachCannotEatMenus;
        this.recommendedMenus = initializeRecommendedMenus(coachCannotEatMenus);
    }

    private List<RecommendedMenus> initializeRecommendedMenus(final List<CoachCannotEatMenus> coachCannotEatMenus) {
        List<RecommendedMenus> menus = new ArrayList<>();
        for (CoachCannotEatMenus coachCannotEatMenu : coachCannotEatMenus) {
            menus.add(new RecommendedMenus(coachCannotEatMenu.getCoachName().getValue(), coachCannotEatMenu));
        }
        return menus;
    }

    public List<RecommendedMenus> makeWeekdayMenus() {
        for (int i = 0; i < WEEKDAY_SIZE; i++) {
            makeTodayMenus();
        }
        return recommendedMenus;
    }

    private void makeTodayMenus() {
        Category category = categoryGenerator.chooseCategory();
        for (int i = 0; i < coachCannotEatMenus.size(); i++) {
            RecommendedMenus recommendedMenu = recommendedMenus.get(i);
            makeRandomMenu(category, recommendedMenu);
        }
    }

    private void makeRandomMenu(final Category category, final RecommendedMenus recommendedMenus) {
        while (true) {
            try {
                List<String> menuNames = Menu.findMenuNames(category);
                Menu menu = Menu.from(menuGenerator.chooseMenu(menuNames));
                recommendedMenus.validate(menu);
                recommendedMenus.addMenu(menu);
                return;
            } catch (IllegalArgumentException ignored) {
                System.out.println(ignored.getMessage());
            }
        }
    }
}
