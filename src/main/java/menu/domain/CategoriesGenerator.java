package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.menu.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoriesGenerator {

    private static final int MAX_DUPLICATE = 2;

    public static List<Category> generate() {
        List<Category> categories = new ArrayList<>();
        int serialNumber = Randoms.pickNumberInRange(1, 5);
        Category category = Category.from(serialNumber);
        if (canAdd(categories, category)) {
            categories.add(category);
        }
        return categories;
    }

    private static boolean canAdd(final List<Category> categories, final Category category) {
        return Collections.frequency(categories, category) < MAX_DUPLICATE;
    }
}
