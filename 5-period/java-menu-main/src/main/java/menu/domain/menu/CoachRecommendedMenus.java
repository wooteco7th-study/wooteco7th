package menu.domain.menu;

import java.util.Collections;
import java.util.List;
import menu.domain.name.Name;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

// - 코치가 이번주에 먹을 메뉴 이름들
//- 5가지의 메뉴
//    - 코치마다 한 주에 **중복되지 않는 메뉴를 추천**한다.
// 못먹는 메뉴
public class CoachRecommendedMenus {

    private static final int RECOMMENDED_MENUS_SIZE = 5;

    private final Name coachName;
    private final List<Menu> eatableMenus;
    private final RecommendedMenus recommendedMenus;

    public CoachRecommendedMenus(final CoachCannotEatMenus coachCannotEatMenus,
                                 final RecommendedMenus recommendedMenus) {
        this.coachName = coachCannotEatMenus.getCoachName();
        this.eatableMenus = Menu.filteredMenus(coachCannotEatMenus.getCannotEatMenus());
        validate(recommendedMenus);
        this.recommendedMenus = recommendedMenus;
    }

    private void validate(final RecommendedMenus menus) {
        validateUniqueSize(menus.getRecommendedMenus());
        validateEatable(menus.getRecommendedMenus());
    }

    private void validateEatable(final List<Menu> menus) {
        if (cannotEat(menus)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_COACH_CANNOT_EAT);
        }
    }

    private boolean cannotEat(final List<Menu> menus) {
        return menus.stream()
                .anyMatch(menu -> !eatableMenus.contains(menu));
    }

    private void validateUniqueSize(final List<Menu> menus) {
        if (menus.size() != RECOMMENDED_MENUS_SIZE) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_RECOMMENDED_SIZE);
        }
    }

    public List<Menu> getRecommendedMenus() {
        return Collections.unmodifiableList(recommendedMenus.getRecommendedMenus());
    }

    public String getCoachName() {
        return coachName.getValue();
    }
}
