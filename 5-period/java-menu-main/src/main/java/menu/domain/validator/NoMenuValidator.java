package menu.domain.validator;

import static menu.constant.ExceptionMessage.INVALID_DUPLICATE_NO_MENU;

import java.util.List;
import menu.util.ListUtils;

public class NoMenuValidator {

    private NoMenuValidator() {
    }

    public static void validateDuplicateNoMenus(List<String> names) {
        if (ListUtils.hasDuplicates(names)) {
            throw new IllegalArgumentException(INVALID_DUPLICATE_NO_MENU.getMessage());
        }
    }
}
