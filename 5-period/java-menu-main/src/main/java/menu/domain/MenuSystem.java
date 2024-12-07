package menu.domain;

import java.util.Collections;
import java.util.List;

public class MenuSystem {

    private final List<Coach> coaches;
    private final MenuTypeGroup menuTypeGroup;

    public MenuSystem(final List<Coach> coaches, final MenuTypeGroup menuTypeGroup) {
        this.coaches = coaches;
        this.menuTypeGroup = menuTypeGroup;
    }

    public void recommend() {
        for (int i = 0; i < coaches.size(); i++) {
            final MenuType menuType = menuTypeGroup.updateMenuTypes();
            final Coach coach = coaches.get(i);
            final String menu = RandomMenuGenerator.generate(coach, menuType);
            coach.addMenu(menu);
        }
    }

    public List<Coach> getCoaches() {
        return Collections.unmodifiableList(coaches);
    }

    public List<String> getMenuTypes() {
        return menuTypeGroup.getMenuTypes();
    }
}
