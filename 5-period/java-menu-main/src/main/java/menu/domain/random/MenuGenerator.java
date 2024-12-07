package menu.domain.random;

import java.util.List;
import menu.domain.menu.Menu;

public interface MenuGenerator {
    Menu chooseMenu(List<String> availableMenuNames);
}
