package menu.domain;

import java.util.List;

public class Coach {

    private final CoachName coachName;
    private final HateMenu hateMenu;
    private final List<String> menus;

    public Coach(final CoachName coachName, final HateMenu hateMenu, final List<String> menus) {
        this.coachName = coachName;
        this.hateMenu = hateMenu;
        this.menus = menus;
    }

    public void addMenu(final String menu) {
        menus.add(menu);
    }

    public boolean hasMenu(final String menu) {
        return menus.contains(menu);
    }

    public boolean isHateMenu(final String menu) {
        return hateMenu.hasMenu(menu);
    }

    public String getCoachName() {
        return coachName.getValue();
    }


    public List<String> getMenus() {
        return menus;
    }
}
