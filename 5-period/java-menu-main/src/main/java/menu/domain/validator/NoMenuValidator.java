package menu.domain.validator;

import static menu.constant.ApplicationRule.BAN_MENU_MAX;
import static menu.constant.ApplicationRule.BAN_MENU_MIN;
import static menu.constant.ExceptionMessage.INVALID_COACH_SIZE;
import static menu.constant.ExceptionMessage.INVALID_DUPLICATE_NO_MENU;

import java.util.List;
import menu.util.ListUtils;

public class NoMenuValidator {

    private NoMenuValidator() {
    }

    public static void validateNoMenuSize(List<String> names) {
        int size = names.size();
        if (!(BAN_MENU_MIN <= size && size <= BAN_MENU_MAX)) {
            throw new IllegalArgumentException(INVALID_COACH_SIZE.getMessage());
        }
    }

    public static void validateDuplicateNoMenus(List<String> names) {
        if (ListUtils.hasDuplicates(names)) {
            throw new IllegalArgumentException(INVALID_DUPLICATE_NO_MENU.getMessage());
        }
    }
}
