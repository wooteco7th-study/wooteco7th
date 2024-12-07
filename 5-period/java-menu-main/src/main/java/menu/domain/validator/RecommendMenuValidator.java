package menu.domain.validator;

/*

  ### [ ] 추천 메뉴 검증
    - 한 주에 같은 카테고리가 최대 2개 이하임을 검증
    - 한 주에 중복되지 않은 메뉴 추천임을 검증
 */

import static menu.constant.ApplicationRule.ONE_WEEK_SAME_CATEGORY_MAX;
import static menu.util.ListUtils.hasDuplicates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.domain.vo.Category;
import menu.domain.vo.DayOfWeek;
import menu.domain.vo.Menu;


public class RecommendMenuValidator {

    private RecommendMenuValidator() {
    }

    public static void main(String[] args) {
        Map<DayOfWeek, Menu> map = new HashMap<>();
        map.put(DayOfWeek.MONDAY, Menu.김밥);
        map.put(DayOfWeek.TUESDAY, Menu.김밥);
        map.put(DayOfWeek.WEDNESDAY, Menu.김밥);
        map.put(DayOfWeek.THURSDAY, Menu.김밥);

        System.out.println(isTwoOrLessOfSameCategory(map));
    }

    public static boolean isTwoOrLessOfSameCategory(Map<DayOfWeek, Menu> recommendMenus) {
        Map<Category, Long> categoryCount = recommendMenus.values().stream()
                .map(Menu::getCategory)
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()));

        return categoryCount.values().stream().noneMatch(count -> count > ONE_WEEK_SAME_CATEGORY_MAX);
    }

    public static boolean isNoDuplicateRecommendMenus(Map<DayOfWeek, Menu> recommendMenus) {
        List<Menu> menus = recommendMenus.values().stream().collect(Collectors.toList());
        return !hasDuplicates(menus);
    }
}
