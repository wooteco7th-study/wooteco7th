package menu.domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class Categories {

    private static final int UNIT = 1;
    private static final int DUPLICATED_CATEGORY_MAX = 2;

    private final List<Category> categories;

    public Categories(final List<Category> categories) {
        validateFrequency(categories);
        this.categories = categories;
    }

    public void addCategory(final Category category) {
        List<Category> temp = new ArrayList<>(categories);
        temp.add(category);
        validateFrequency(temp);
        categories.add(category);
    }

    private void validateFrequency(final List<Category> categories) {
        for (Category category : categories) {
            int frequency = Collections.frequency(categories, category);
            if (frequency > DUPLICATED_CATEGORY_MAX) {
                throw new CustomIllegalArgumentException(ErrorMessage.INVALID_CATEGORY_DUPLICATED);
            }
        }
    }

    public Category getCategory(int index) {
        return categories.get(index);
    }
}
