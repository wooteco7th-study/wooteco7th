package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMenuTypeGenerator {

    private RandomMenuTypeGenerator() {
    }

    public static MenuType generate(final int min, final int max) {
        final int number = Randoms.pickNumberInRange(min, max);
        return MenuType.findByNumber(number);
    }
}
