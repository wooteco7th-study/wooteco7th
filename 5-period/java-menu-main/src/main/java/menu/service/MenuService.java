package menu.service;

import java.util.ArrayList;
import java.util.List;
import menu.domain.menu.Categories;
import menu.domain.menu.CoachCannotEatMenus;
import menu.domain.menu.CoachRecommendedMenus;
import menu.domain.recommender.MenuRecommender;
import menu.dto.ResultDto;

public class MenuService {
    public ResultDto makeRecommendedMenus(final MenuRecommender menuRecommender, final Categories categories,
                                          final List<CoachCannotEatMenus> menusCannotEat) {
        List<CoachRecommendedMenus> coachRecommendedMenus = new ArrayList<>();
        for (CoachCannotEatMenus coachCannotEatMenus : menusCannotEat) {
            coachRecommendedMenus.add(makeRecommendMenusEach(menuRecommender, coachCannotEatMenus));
        }
        return ResultDto.of(coachRecommendedMenus);
    }

    private CoachRecommendedMenus makeRecommendMenusEach(final MenuRecommender menuRecommender,
                                                         final CoachCannotEatMenus coachCannotEatMenus) {
        return menuRecommender.makeWeekDayMenus(coachCannotEatMenus);
    }
}
