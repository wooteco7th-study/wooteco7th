package menu.domain.menu;

import java.util.ArrayList;
import java.util.List;
import menu.domain.name.Name;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class Coach {

    private static final int MAX_HATE_MENU_NUMBER = 2;

    private final Name coachName;
    private final Menus hateMenus;
    private final Menus recommendedMenus;

    public Coach(final Name coachName, final List<String> hateMenus,
                 final List<String> recommendedMenus) {
        validateHateMenus(hateMenus);
        this.coachName = coachName;
        this.hateMenus = Menus.of(hateMenus);
        this.recommendedMenus = Menus.of(recommendedMenus);;
    }

    public void addRecommendMenu(final Menu menu) {
        List<Menu> candidate = new ArrayList<>(recommendedMenus.getMenus());
        candidate.add(menu);
        validateMenu(candidate);
        recommendedMenus.add(menu);
    }

    private void validateHateMenus(final List<String> menus) {
        if (menus.size() > MAX_HATE_MENU_NUMBER) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME_NUMBER);
        }
    }

    private void validateMenu(final List<Menu> menus) {
        validateDuplicated(menus);
        validateCanEat(menus);
    }

    private void validateDuplicated(final List<Menu> menus) {
        if (menus.size() != menus.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME_DUPLICATED);
        }
    }

    private void validateCanEat(final List<Menu> menus) {
        if (cannotEat(menus)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_COACH_CANNOT_EAT);
        }
    }

    private boolean cannotEat(final List<Menu> menus) {
        return menus.stream().anyMatch(hateMenus::contains);
    }

    public Menus getRecommendedMenus() {
        return recommendedMenus;
    }

    public Name getCoachName() {
        return coachName;
    }
}
