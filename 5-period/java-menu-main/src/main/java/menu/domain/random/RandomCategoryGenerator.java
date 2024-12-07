package menu.domain.random;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.menu.Category;

public class RandomCategoryGenerator implements CategoryGenerator {

    @Override
    public Category chooseCategory() {
        return Category.from(Randoms.pickNumberInRange(1, 5));
    }
}
