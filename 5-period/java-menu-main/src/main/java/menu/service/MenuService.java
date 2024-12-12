package menu.service;

import java.util.List;
import menu.domain.menu.Coach;
import menu.domain.recommender.MenuRecommender;
import menu.dto.ResultDto;

public class MenuService {
    public ResultDto makeRecommendedMenus(final MenuRecommender menuRecommender) {
        List<Coach> coachRecommendedMenus = makeRecommendMenus(menuRecommender);
        return ResultDto.of(coachRecommendedMenus);
    }

    private List<Coach> makeRecommendMenus(final MenuRecommender menuRecommender) {
        return menuRecommender.makeWeekdayMenus();
    }
}
