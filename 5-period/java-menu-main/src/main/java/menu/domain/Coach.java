package menu.domain;

import static menu.domain.validator.CoachValidator.validateCoachNameRange;

import java.util.ArrayList;
import java.util.List;
import menu.domain.vo.Menu;

public class Coach {
    private final String name;
    private final List<Menu> noMenus;

    public Coach(final String name) {
        validateCoachNameRange(name);
        this.name = name;
        this.noMenus = new ArrayList<>();
    }

    public void addNoMenus(final List<Menu> menus) {
        noMenus.addAll(menus);
    }

    public String getName() {
        return name;
    }

    public List<Menu> getNoMenus() {
        return noMenus;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "name='" + name + '\'' +
                '}';
    }

}
