package menu.domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class RecommendedMenus {

    private final List<Menu> eatableMenus;
    private final List<Menu> recommendedMenus;

    public RecommendedMenus(final CoachCannotEatMenus coachCannotEatMenus, final List<Menu> recommendedMenus) {
        this.eatableMenus = Menu.filteredMenus(coachCannotEatMenus.getCannotEatMenus());
        validate(recommendedMenus); // 크기 5개는 CoauchRecommendedMenus에서 검증, 먹을 수 있는지만 검증
        this.recommendedMenus = new ArrayList<>(recommendedMenus);
    }

    public RecommendedMenus(final List<Menu> eatableMenus, final List<Menu> recommendedMenus) {
        this.eatableMenus = new ArrayList<>(eatableMenus);
        validate(recommendedMenus); // 크기 5개는 CoauchRecommendedMenus에서 검증, 먹을 수 있는지만 검증
        this.recommendedMenus = new ArrayList<>(recommendedMenus);
    }

    public RecommendedMenus addMenu(final Menu inputMenu) {
        recommendedMenus.add(inputMenu);
        return new RecommendedMenus(eatableMenus, recommendedMenus);
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

    public List<Menu> getRecommendedMenus() {
        return Collections.unmodifiableList(recommendedMenus);
    }
}
