package menu.domain.random;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import menu.domain.menu.Menu;

public class RandomMenuGenerator implements MenuGenerator {

    public String chooseMenu(List<String> availableMenuNames) {
        return Randoms.shuffle(availableMenuNames).get(0);
    }
}
