package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class RecommendationMachine {

    public static List<Menu> recommendMenu(final CantEatMenu cantEatMenu) {
        List<Menu> menuList = new ArrayList<>();
        List<Category> categories = CategoriesGenerator.generate();
        for (Category category : categories) {
            addMenu(category, menuList, cantEatMenu);
        }
        return menuList;
    }

    private static void addMenu(final Category category, final List<Menu> menuList, final CantEatMenu cantEatMenu) {
        List<String> allMenu = Menu.findAllMenuInSameCategory(category);
        while (true) {
            String menuName = Randoms.shuffle(allMenu).getFirst();
            Menu menu = Menu.from(menuName);
            if (canAdd(menuList, cantEatMenu, menu)) {
                menuList.add(menu);
                break;
            }
        }
    }

    private static boolean canAdd(final List<Menu> menuList, final CantEatMenu cantEatMenu, final Menu menu) {
        return !menuList.contains(menu) && !cantEatMenu.contains(menu);
    }
}
