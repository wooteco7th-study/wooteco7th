package menu.domain.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class Categories {

    private static final int UNIT = 1;
    private static final int DUPLICATED_CATEGORY_MAX = 2;

    private final List<Category> categories;

    public Categories(final List<Category> categories) {
        validate(categories);
        this.categories = categories;
    }

    public void addCategory(final Category category) {
        List<Category> temp = new ArrayList<>(categories);
        temp.add(category);
        validate(temp);
        categories.add(category);
    }

    private void validate(final List<Category> categories) {
        Map<Category, Integer> categoryFrequency = new HashMap<>();
        for (Category category : categories) {
            categoryFrequency.merge(category, UNIT, Integer::sum);
        }
        if (exceedMaxLimit(categoryFrequency)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_CATEGORY_DUPLICATED);
        }
    }

    private boolean exceedMaxLimit(final Map<Category, Integer> categoryFrequency) {
        return categoryFrequency.values().stream()
                .anyMatch(value -> value > DUPLICATED_CATEGORY_MAX);
    }

    public Category getCategory(int index) {
        return categories.get(index);
    }

    public int getSize() {
        return categories.size();
    }
}
