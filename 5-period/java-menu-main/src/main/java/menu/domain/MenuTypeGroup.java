package menu.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MenuTypeGroup {

    private static final int MIN = 1;
    private static final int MAX = 5;
    private final List<MenuType> menuTypes;

    public MenuTypeGroup(final List<MenuType> menuTypes) {
        this.menuTypes = menuTypes;
    }

    public MenuType updateMenuTypes() {
        final MenuType menuType = RandomMenuTypeGenerator.generate(MIN, MAX);
        if (Collections.frequency(menuTypes, menuType) < 2) {
            menuTypes.add(menuType);
            return menuType;
        }
        return updateMenuTypes();
    }

    public List<String> getMenuTypes() {
        return menuTypes.stream()
                .map(MenuType::getName)
                .collect(Collectors.toList());
    }
}
