package menu.service;

import java.util.List;
import menu.domain.menu.RecommendedMenus;
import menu.domain.recommender.MenuRecommender;
import menu.dto.ResultDto;

public class MenuService {
    public ResultDto makeRecommendedMenus(final MenuRecommender menuRecommender) {
        List<RecommendedMenus> coachRecommendedMenus = makeRecommendMenus(menuRecommender);
        return ResultDto.of(coachRecommendedMenus);
    }

    private List<RecommendedMenus> makeRecommendMenus(final MenuRecommender menuRecommender) {
        return menuRecommender.makeWeekdayMenus();
    }
}
