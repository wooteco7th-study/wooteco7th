package menu.service;

import static menu.domain.validator.RecommendMenuValidator.isNoDuplicateRecommendMenus;
import static menu.domain.validator.RecommendMenuValidator.isTwoOrLessOfSameCategory;
import static menu.domain.vo.Category.pickRandomCategory;
import static menu.domain.vo.DayOfWeek.findByIndex;
import static menu.domain.vo.Menu.pickRandomByCategory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.domain.vo.Category;
import menu.domain.vo.DayOfWeek;
import menu.domain.vo.Menu;


public class MenuRecommendGenerator {

    public Map<DayOfWeek, Menu> generate(final List<Menu> noMenus) {
        while (true) {
            Map<DayOfWeek, Menu> recommend = new LinkedHashMap<>();
            for (int i = 0; i < 5; i++) {
                Category category = pickRandomCategory();
                DayOfWeek dayOfWeek = findByIndex(i);
                Menu menu = pickRandomByCategory(category, noMenus);
                recommend.put(dayOfWeek, menu);
            }
            if (isTwoOrLessOfSameCategory(recommend) && isNoDuplicateRecommendMenus(recommend)) {
                return recommend;
            }
        }
    }


}
