package menu.controller;

import menu.domain.CantEatMenu;
import menu.domain.CategoriesGenerator;
import menu.domain.Coach;
import menu.domain.CoachName;
import menu.domain.Coaches;
import menu.domain.RecommendationMachine;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static menu.exception.ExceptionHandler.retryOnInvalidInput;

public class MenuController {
    private final InputView inputView;
    private final OutputView outputView;

    public MenuController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        RecommendationMachine recommendationMachine = new RecommendationMachine(CategoriesGenerator.generate());
        Coaches coaches = retryOnInvalidInput(this::createCoaches);
        putCantEatMenuPerCoach(coaches);
        Map<CoachName, List<Menu>> recommendMenu = recommendationMachine.recommendMenu(coaches);
        List<Category> categories = recommendationMachine.getCategories();
        outputView.printResult(categories, recommendMenu, coaches);
    }

    private Coaches createCoaches() {
        List<String> names = retryOnInvalidInput(inputView::readCoachNames);
        return new Coaches(names.stream()
                .map(CoachName::new)
                .map(Coach::new)
                .collect(Collectors.toList()));
    }

    private void putCantEatMenuPerCoach(final Coaches coaches) {
        for (Coach coach : coaches.getCoaches()) {
            CantEatMenu cantEatMenu = retryOnInvalidInput(() -> createCantEatMenu(coach.getCoachName()));
            coach.putCantEatMenu(cantEatMenu);
        }
    }

    private CantEatMenu createCantEatMenu(CoachName name) {
        List<String> cantEatMenu = inputView.readCoachCantEatMenu(name.getName());
        return new CantEatMenu(cantEatMenu
                .stream()
                .map(Menu::from)
                .collect(Collectors.toList()));
    }
}
