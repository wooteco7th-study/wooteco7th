package menu.dto;

import java.util.List;
import menu.domain.Coach;

public record MenuResult(

        String coachName,
        List<String> menus
) {
    public static MenuResult of(final Coach coach) {
        return new MenuResult(
                coach.getCoachName(),
                coach.getMenus()
        );
    }
}
