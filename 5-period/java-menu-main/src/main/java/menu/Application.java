package menu;

import menu.controller.MenuController;
import menu.exception.ExceptionHandler;
import menu.service.MenuService;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {

    public static void main(String[] args) {
        MenuController menuController = makeMenuController();
        menuController.process();
    }

    private static MenuController makeMenuController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        MenuService menuService = new MenuService();
        return new MenuController(inputView, outputView, exceptionHandler, menuService);
    }
}
