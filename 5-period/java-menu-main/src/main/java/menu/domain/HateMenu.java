package menu.domain;

import java.util.List;
import menu.error.ErrorMessage;
import menu.util.ListValidator;

public class HateMenu {

    private static final int MIN = 0;
    private static final int MAX = 2;
    private final List<String> values;

    public HateMenu(final List<String> values) {
        validate(values);
        this.values = values;
    }

    public boolean hasMenu(final String menu) {
        return values.contains(menu);
    }

    private void validate(final List<String> values) {
        ListValidator.validateSize(values, MIN, MAX, ErrorMessage.INVALID_HATE_MENU);
        ListValidator.validateDuplicate(values, ErrorMessage.INVALID_DUPLICATED_HATE_MENU);
    }
}
