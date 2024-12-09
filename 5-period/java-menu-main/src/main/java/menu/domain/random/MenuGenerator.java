package menu.domain.random;

import java.util.List;
import menu.domain.menu.Menu;

public interface MenuGenerator {
    String chooseMenu(List<String> availableMenuNames);
}
