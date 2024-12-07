package menu.controller;

import menu.domain.Names;
import menu.exception.ErrorMessage;
import menu.exception.ExceptionHandler;
import menu.service.MenuService;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final MenuService menuService;

    public MenuController(final InputView inputView, final OutputView outputView,
                          final ExceptionHandler exceptionHandler,
                          final MenuService menuService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.menuService = menuService;
    }

    public void process() {
        // 코치 이름 입력 기능 구현
        Names names = makeNames();



    }

    private Names makeNames() {
        outputView.showTitleWelcome();
        return new Names(inputView.readCoachName(ErrorMessage.INVALID_INPUT_FORMAT));
    }
}
