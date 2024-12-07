package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendationMachine {

    private final List<Category> categories;

    public RecommendationMachine(final List<Category> categories) {
        this.categories = categories;
    }

    public Map<CoachName, List<Menu>> recommendMenu(final Coaches coaches) {
        Map<CoachName, List<Menu>> result = initResult(coaches);
        for (Category category : categories) {
            addMenuPerCoach(coaches, category, result);
        }
        return result;
    }

    public List<Category> getCategories() {
        return categories;
    }

    private Map<CoachName, List<Menu>> initResult(Coaches coaches) {
        Map<CoachName, List<Menu>> result = new HashMap<>();
        for (CoachName coachName : coaches.getCoachNames()) {
            result.put(coachName, new ArrayList<>());
        }
        return result;
    }

    private void addMenuPerCoach(final Coaches coaches, final Category category, final Map<CoachName, List<Menu>> result) {
        for (Coach coach : coaches.getCoaches()) {
            addMenu(category, result, coach);
        }
    }

    private void addMenu(final Category category, final Map<CoachName, List<Menu>> result, final Coach coach) {
        List<String> allMenu = Menu.findAllMenuInSameCategory(category);
        while (true) {
            String menuName = Randoms.shuffle(allMenu).getFirst();
            Menu menu = Menu.from(menuName);
            CoachName coachName = coach.getCoachName();
            if (canAdd(result.get(coachName), coach.getCantEatMenu(), menu)) {
                result.get(coachName).add(menu);
                break;
            }
        }
    }

    private boolean canAdd(final List<Menu> menuList, final CantEatMenu cantEatMenu, final Menu menu) {
        return !menuList.contains(menu) && !cantEatMenu.contains(menu);
    }
}
