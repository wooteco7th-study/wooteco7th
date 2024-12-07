package menu.service;

import java.util.List;
import menu.domain.menu.Categories;
import menu.domain.menu.CoachCannotEatMenus;
import menu.domain.menu.CoachRecommendedMenus;
import menu.domain.recommender.MenuRecommender;
import menu.dto.ResultDto;

public class MenuService {
    public ResultDto makeRecommendedMenus(final MenuRecommender menuRecommender, final Categories categories,
                                          final List<CoachCannotEatMenus> menusCannotEat) {
        List<CoachRecommendedMenus> coachRecommendedMenus = makeRecommendMenusEach(menuRecommender, menusCannotEat);
        return ResultDto.of(coachRecommendedMenus);
    }

    private List<CoachRecommendedMenus> makeRecommendMenusEach(final MenuRecommender menuRecommender,
                                                               final List<CoachCannotEatMenus> coachCannotEatMenus) {
        return menuRecommender.makeWeekDayMenus(coachCannotEatMenus);
    }
}
