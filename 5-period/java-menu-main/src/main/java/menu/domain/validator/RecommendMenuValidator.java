package menu.domain.validator;

/*

  ### [ ] 추천 메뉴 검증
    - 한 주에 같은 카테고리가 최대 2개 이하임을 검증
    - 한 주에 중복되지 않은 메뉴 추천임을 검증
 */

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

    private static boolean isTwoOrLessOfSameCategory(Map<DayOfWeek, Menu> recommendMenus) {
        List<Category> categories = recommendMenus.values().stream().map(menu -> menu.getCategory())
                .collect(Collectors.toList());
        for (int i = 0; i < categories.size(); i++) {
            int sameCount = 0;
            Category category = categories.get(i);
            for (int j = i + 1; j < categories.size(); j++) {
                if (categories.get(j).equals(category)) {
                    ++sameCount;
                }
            }
            if (sameCount >= 2) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateNoDuplicateRecommendMenus(Map<DayOfWeek, Menu> recommendMenus) {
        List<Menu> menus = recommendMenus.values().stream().collect(Collectors.toList());
        return hasDuplicates(menus);
    }
}
