package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.menu.CoachCannotEatMenus;
import menu.domain.name.Name;
import menu.domain.name.Names;
import menu.domain.random.CategoryGenerator;
import menu.domain.random.MenuGenerator;
import menu.domain.recommender.MenuRecommender;
import menu.dto.ResultDto;
import menu.exception.ErrorMessage;
import menu.exception.ExceptionHandler;
import menu.service.MenuService;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    private final InputView inputView;
    private final OutputView outputView;
    private final MenuService menuService;
    private final CategoryGenerator categoryGenerator;
    private final MenuGenerator menuGenerator;

    public MenuController(final InputView inputView, final OutputView outputView,
                          final MenuService menuService, final CategoryGenerator categoryGenerator,
                          final MenuGenerator menuGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.menuService = menuService;
        this.categoryGenerator = categoryGenerator;
        this.menuGenerator = menuGenerator;
    }

    public void process() {
        // 코치 이름 입력 기능 구현
        Names names = makeNames();
        // 코치별 못 먹는 메뉴 입력 기능 구현
        List<CoachCannotEatMenus> menusCannotEat = makeMenus(names);
        // 코치별 메뉴 추천 기능 구현
        recommendMenus(menusCannotEat);
    }

    private void recommendMenus(final List<CoachCannotEatMenus> menusCannotEat) {
        MenuRecommender menuRecommender = new MenuRecommender(categoryGenerator, menuGenerator, menusCannotEat);
        ResultDto resultDto = menuService.makeRecommendedMenus(menuRecommender);
        outputView.showTitleResult(resultDto);
        outputView.showTitleEndResult();
    }

    private List<CoachCannotEatMenus> makeMenus(final Names names) {
        List<CoachCannotEatMenus> menus = new ArrayList<>();
        for (Name name : names.getNames()) {
            outputView.showRequestMenu(name.getValue());
            menus.add(makeCoachMenus(name));
        }
        return menus;
    }

    private CoachCannotEatMenus makeCoachMenus(final Name name) {
        return ExceptionHandler.retryUntilSuccess(
                () -> new CoachCannotEatMenus(name, inputView.readMenuCannotEat(ErrorMessage.INVALID_INPUT)));
    }

    private Names makeNames() {
        outputView.showTitleWelcome();
        return ExceptionHandler.retryUntilSuccess(
                () -> new Names(inputView.readCoachName(ErrorMessage.INVALID_INPUT_FORMAT)));
    }
}
