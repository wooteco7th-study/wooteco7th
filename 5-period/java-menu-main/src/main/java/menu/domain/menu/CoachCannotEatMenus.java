package menu.domain.menu;

import java.util.Collections;
import java.util.List;
import menu.domain.name.Name;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class CoachCannotEatMenus {

    private static final int MAX_MENU_NUMBER = 2;

    private final Name coachName;
    private final List<Menu> cannotEatMenus;

    public CoachCannotEatMenus(final Name coachName, final List<String> cannotEatMenus) {
        validateUniqueSize(cannotEatMenus);
        this.coachName = coachName;
        this.cannotEatMenus = makeMenus(cannotEatMenus);
    }

    private List<Menu> makeMenus(final List<String> inputs) {
        return inputs.stream()
                .map(Menu::from)
                .toList();
    }

    private void validateUniqueSize(final List<String> menus) {
        if (menus.size() != menus.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME_DUPLICATED);
        }
        if (menus.size() > MAX_MENU_NUMBER) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME_NUMBER);
        }
    }

    public List<Menu> getCannotEatMenus() {
        return Collections.unmodifiableList(cannotEatMenus);
    }

    public Name getCoachName() {
        return coachName;
    }
}
