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

    public RecommendedMenus(final String coachName, final CoachCannotEatMenus coachCannotEatMenus) {
        this.coachName = coachName;
        this.eatableMenus = Menu.filteredMenus(coachCannotEatMenus.getCannotEatMenus());
        this.recommendedMenus = new ArrayList<>();
    }

    public void addMenu(final Menu inputMenu) {
        recommendedMenus.add(inputMenu);
    }

    // validate에서 recommendedMenus.add(menu)한 후 검증 수행 -> 예외 발생
    // 먼저 validate 메서드 호출 후에 예외 발생시킨 후 정상적인 상황에서만 recommendedMenus.add 수행 -> 성공
    public void validate(final Menu menu) {
        List<Menu> menus = new ArrayList<>(recommendedMenus);
        menus.add(menu);
        validate(menus);
    }

    private void validate(final List<Menu> menus) {
        validateUniqueSize(menus);
        validateCanEat(menus);
    }

    private void validateCanEat(final List<Menu> menus) {
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
