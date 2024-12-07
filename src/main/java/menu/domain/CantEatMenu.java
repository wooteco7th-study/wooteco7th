package menu.domain;

import menu.domain.menu.Menu;

import java.util.List;

import static menu.exception.ExceptionMessage.MENU_OUT_OF_RANGE;

public class CantEatMenu {
    public static final int MAX_SIZE = 2;

    private final CoachName name;
    private final List<Menu> cantEatMenu;

    public CantEatMenu(final CoachName name, final List<Menu> cantEatMenu) {
        this.name = name;
        validateRange(cantEatMenu);
        this.cantEatMenu = cantEatMenu;
    }

    public boolean contains(final Menu menu) {
        return cantEatMenu.contains(menu);
    }

    private void validateRange(final List<Menu> cantEatMenu) {
        if (isOutOfRange(cantEatMenu)) {
            throw new IllegalArgumentException(MENU_OUT_OF_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(final List<Menu> cantEatMenu) {
        return cantEatMenu.size() > MAX_SIZE;
    }
}
