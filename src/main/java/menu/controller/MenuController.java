package menu.controller;

import menu.domain.CantEatMenu;
import menu.domain.CoachName;
import menu.domain.Coaches;
import menu.domain.menu.Menu;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.List;

import static menu.exception.ExceptionHandler.retryOnInvalidInput;

public class MenuController {
    private final InputView inputView;
    private final OutputView outputView;

    public MenuController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Coaches coaches = retryOnInvalidInput(this::createCoaches);
        for (CoachName name : coaches.getCoaches()) {
            CantEatMenu cantEatMenu = retryOnInvalidInput(() -> createCantEatMenu(name));
        }

    }

    private Coaches createCoaches() {
        List<String> names = retryOnInvalidInput(inputView::readCoachNames);
        return new Coaches(names.stream()
                .map(CoachName::new)
                .toList());
    }

    private CantEatMenu createCantEatMenu(CoachName name) {
        List<String> cantEatMenu = inputView.readCoachCantEatMenu(name.getName());
        return new CantEatMenu(name,
                cantEatMenu
                        .stream()
                        .map(Menu::from)
                        .toList());
    }
}
