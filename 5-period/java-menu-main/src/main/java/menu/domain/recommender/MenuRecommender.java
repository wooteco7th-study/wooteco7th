package menu.domain.recommender;

import java.util.ArrayList;
import java.util.List;
import menu.domain.category.Categories;
import menu.domain.category.Category;
import menu.domain.menu.Coach;
import menu.domain.menu.Menu;
import menu.domain.random.CategoryGenerator;
import menu.domain.random.MenuGenerator;
import menu.exception.ExceptionHandler;

public class MenuRecommender {

    private static final int WEEKDAY_SIZE = 5;

    private final CategoryGenerator categoryGenerator;
    private final MenuGenerator menuGenerator;
    private final List<Coach> coaches;
    private final Categories categories;

    public MenuRecommender(final CategoryGenerator categoryGenerator, final MenuGenerator menuGenerator,
                           final List<Coach> coaches) {
        this.categoryGenerator = categoryGenerator;
        this.menuGenerator = menuGenerator;
        this.coaches = coaches;
        this.categories = new Categories(new ArrayList<>());
    }

    public List<Coach> makeWeekdayMenus() {
        for (int i = 0; i < WEEKDAY_SIZE; i++) {
            addCategory();
            makeTodayMenus(categories.getCategory(i));
        }
        return coaches;
    }

    private void makeTodayMenus(final Category category) {
        for (Coach coach : coaches) {
            makeRandomMenu(category, coach);
        }
    }

    private void addCategory() {
        ExceptionHandler.retryUntilSuccess(() -> {
            Category category = categoryGenerator.chooseCategory();
            categories.addCategory(category);
        });
    }

    private void makeRandomMenu(final Category category, final Coach coach) {
        ExceptionHandler.retryUntilSuccess(() -> {
            List<String> menuNames = Menu.findMenuNames(category);
            Menu menu = Menu.fromActualMenus(menuGenerator.chooseMenu(menuNames));
            coach.addRecommendMenu(menu);
        });
    }
}
