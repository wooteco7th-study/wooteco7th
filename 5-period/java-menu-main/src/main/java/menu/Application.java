package menu;

import camp.nextstep.edu.missionutils.Console;
import menu.controller.MenuController;
import menu.domain.random.CategoryGenerator;
import menu.domain.random.MenuGenerator;
import menu.domain.random.RandomCategoryGenerator;
import menu.domain.random.RandomMenuGenerator;
import menu.exception.ExceptionHandler;
import menu.service.MenuService;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {

    public static void main(String[] args) {
        MenuController menuController = makeMenuController();
        try {
            menuController.process();
        } finally {
            Console.close();
        }
    }

    private static MenuController makeMenuController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        MenuService menuService = new MenuService();
        CategoryGenerator categoryGenerator = new RandomCategoryGenerator();
        MenuGenerator menuGenerator = new RandomMenuGenerator();
        return new MenuController(inputView, outputView, exceptionHandler, menuService, categoryGenerator,
                menuGenerator);
    }
}
