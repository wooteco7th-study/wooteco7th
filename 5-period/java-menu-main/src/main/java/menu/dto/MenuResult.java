package menu.dto;

import java.util.List;
import menu.domain.Coach;

public class MenuResult {

    private final String coachName;
    private final List<String> menus;

    public MenuResult(final String coachName, final List<String> menus) {
        this.coachName = coachName;
        this.menus = menus;
    }

    public static MenuResult of(final Coach coach) {
        return new MenuResult(
                coach.getCoachName(),
                coach.getMenus()
        );
    }

    public String getCoachName() {
        return coachName;
    }

    public List<String> getMenus() {
        return menus;
    }
}
