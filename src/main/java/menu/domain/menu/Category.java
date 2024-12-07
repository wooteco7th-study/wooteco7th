package menu.domain.menu;

import java.util.Arrays;

import static menu.exception.ExceptionMessage.CATEGORY_NOT_FOUND;

public enum Category {
    없음(0),
    일식(1),
    한식(2),
    중식(3),
    아시안(4),
    양식(5);

    private final int serialNumber;

    Category(final int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public static Category from(int number) {
        return Arrays.stream(Category.values())
                .filter(element -> element.serialNumber == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CATEGORY_NOT_FOUND.getMessage()));
    }
}

