package menu.domain.random;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import menu.domain.menu.Menu;

public class RandomMenuGenerator implements MenuGenerator {

    public Menu chooseMenu(List<String> availableMenuNames) {
        return Menu.from(Randoms.shuffle(availableMenuNames).get(0));
    }
}
