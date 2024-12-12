package menu.domain.random;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.category.Category;

public class RandomCategoryGenerator implements CategoryGenerator {

    private static final int START_INCLUSIVE = 1;
    private static final int END_INCLUSIVE = 5;

    @Override
    public Category chooseCategory() {
        return Category.from(Randoms.pickNumberInRange(START_INCLUSIVE, END_INCLUSIVE));
    }
}
