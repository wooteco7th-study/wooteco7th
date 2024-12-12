package menu.domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class Menus {

    private final List<Menu> menus;

    public Menus(final List<Menu> menus) {
        validate(menus);
        this.menus = new ArrayList<>(menus);
    }

    public static Menus of(final List<String> inputs) {
        return new Menus(inputs.stream()
                .map(Menu::from)
                .toList());
    }

    public void add(final Menu menu) {
        menus.add(menu);
    }

    public boolean contains(final Menu menu) {
        return menus.contains(menu);
    }

    private void validate(final List<Menu> menus) {
        if (menus.size() != menus.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_DUPLICATED);
        }
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }
}
