package menu.domain.menu;

import java.util.Arrays;

import static menu.exception.ExceptionMessage.CATEGORY_NOT_FOUND;

public enum Category {
    없음,
    일식,
    한식,
    중식,
    아시안,
    양식;

    public static Category from(String input) {
        return Arrays.stream(Category.values())
                .filter(element -> element.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CATEGORY_NOT_FOUND.getMessage()));
    }
}

