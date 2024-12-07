package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMenuGenerator {

    private static final int FIRST = 0;

    private RandomMenuGenerator() {
    }

    public static String generate(final Coach coach, final MenuType menuType) {
        final String menu = Randoms.shuffle(menuType.getMenus()).get(FIRST);
        if (!coach.isHateMenu(menu) && !coach.hasMenu(menu)) {
            return menu;
        }
        return generate(coach, menuType);
    }
}
