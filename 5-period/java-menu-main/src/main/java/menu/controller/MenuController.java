package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.menu.CoachMenus;
import menu.domain.name.Name;
import menu.domain.name.Names;
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
        // 코치별 못 먹는 메뉴 입력 기능 구현
        List<CoachMenus> menusCannotEat = makeMenus(names);


    }

    private List<CoachMenus> makeMenus(final Names names) {
        List<CoachMenus> menus = new ArrayList<>();
        for (Name name : names.getNames()) {
            outputView.showRequestMenu(name.getValue());
            menus.add(makeCoachMenus(name));
        }
        return menus;
    }

    private CoachMenus makeCoachMenus(final Name name) {
        return exceptionHandler.retryUntilSuccess(
                () -> new CoachMenus(name, inputView.readMenuCannotEat(ErrorMessage.INVALID_INPUT)));
    }

    private Names makeNames() {
        outputView.showTitleWelcome();
        return exceptionHandler.retryUntilSuccess(
                () -> new Names(inputView.readCoachName(ErrorMessage.INVALID_INPUT_FORMAT)));
    }
}
