package menu.domain.recommender;

import java.util.ArrayList;
import menu.domain.menu.Categories;
import menu.domain.random.CategoryGenerator;

public class CategoryRecommender {

    private static final int WEEKDAY_SIZE = 5;
    private final CategoryGenerator categoryGenerator;

    public CategoryRecommender(final CategoryGenerator categoryGenerator) {
        this.categoryGenerator = categoryGenerator;
    }

    // - 이번 주의 카테고리 생성기
    //    - [ ]  매 요일마다 5번 뽑음 + 중복시 다시 뽑음
    //    - [ ]  `1이면 일식, 2면 한식, 3이면 중식, 4면 아시안, 5면 양식`
    public Categories makeWeekDayCategories() {
        Categories categories = new Categories(new ArrayList<>());
        while (categories.getSize() != WEEKDAY_SIZE) {
            categories = addRandomCategory(categories);
        }
        return categories;
    }

    private Categories addRandomCategory(final Categories categories) {
        while (true) {
            try {
                return categories.addCategory(categoryGenerator.chooseCategory());
            } catch (IllegalArgumentException ignored) {
            }
        }
    }
}
