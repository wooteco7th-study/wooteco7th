package menu.controller;

import menu.domain.CoachName;
import menu.domain.Coaches;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.ArrayList;
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
    }

    private Coaches createCoaches() {
        List<String> names = retryOnInvalidInput(inputView::readCoachNames);
        List<CoachName> coachNames = new ArrayList<>();
        for (String name : names) {
            coachNames.add(new CoachName(name));
        }
        return new Coaches(coachNames);
    }
}
