package menu;

import menu.controller.MenuController;
import menu.view.console.ConsoleInputView;
import menu.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final ConsoleInputView consoleInputView = new ConsoleInputView();
        final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        final MenuController menuController = new MenuController(consoleInputView, consoleOutputView);
        menuController.run();
    }
}
