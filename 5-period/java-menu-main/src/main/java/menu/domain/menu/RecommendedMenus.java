package menu.domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class RecommendedMenus {

    private final String coachName;
    private final List<Menu> eatableMenus;
    private final List<Menu> recommendedMenus;

    public RecommendedMenus(final String coachName, final CoachCannotEatMenus coachCannotEatMenus, final List<Menu> recommendedMenus) {
        this.coachName = coachName;
        this.eatableMenus = Menu.filteredMenus(coachCannotEatMenus.getCannotEatMenus());
        validate(recommendedMenus);
        this.recommendedMenus = new ArrayList<>(recommendedMenus);
    }

    public void addMenu(final Menu inputMenu) {
        recommendedMenus.add(inputMenu);
        System.out.println(recommendedMenus);
        validate(recommendedMenus);
    }

    private void validate(final List<Menu> menus) {
        validateUniqueSize(menus);
        if (cannotEat(menus)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_COACH_CANNOT_EAT);
        }
    }

    private void validateUniqueSize(final List<Menu> menus) {
        if (menus.size() != menus.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME_DUPLICATED);
        }
    }


    private boolean cannotEat(final List<Menu> menus) {
        return menus.stream()
                .anyMatch(menu -> !eatableMenus.contains(menu));
    }

    public String getCoachName() {
        return coachName;
    }

    public List<Menu> getRecommendedMenus() {
        return Collections.unmodifiableList(recommendedMenus);
    }
}
