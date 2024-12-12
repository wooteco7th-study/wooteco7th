package menu.domain.category;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public enum Category {

    일식(1), 한식(2), 중식(3), 아시안(4), 양식(5), NONE(0);

    private static final List<Category> VALUED_CATEGORIES = Arrays.stream(Category.values())
            .filter(category -> category != NONE)
            .toList();

    private final int number;

    Category(final int number) {
        this.number = number;
    }

    public static List<Category> getValued() {
        return VALUED_CATEGORIES;
    }

    public static Category from(final int number) {
        return VALUED_CATEGORIES.stream()
                .filter(category -> Objects.equals(category.number, number))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_CATEGORY_NUMBER));
    }
}
