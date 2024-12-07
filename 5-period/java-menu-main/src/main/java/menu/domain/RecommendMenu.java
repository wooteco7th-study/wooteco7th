package menu.domain;

import java.util.Map;
import menu.domain.vo.DayOfWeek;
import menu.domain.vo.Menu;

public class RecommendMenu {
    private final String name;
    private final Map<DayOfWeek, Menu> recommendMenus;

    public RecommendMenu(final String name, final Map<DayOfWeek, Menu> recommendMenus) {
        this.name = name;
        this.recommendMenus = recommendMenus;
    }
}
