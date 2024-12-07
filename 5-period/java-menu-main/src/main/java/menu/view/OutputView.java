package menu.view;

import java.util.List;
import menu.dto.MenuResult;

public interface OutputView {

    void printIntro();

    void printAskCoachesName();

    void printAskHateMenu(final String name);

    void printResult(final List<String> menuTypes, final List<MenuResult> menuResults);

    void printClear();
}
