package menu.controller;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Coach;
import menu.domain.CoachName;
import menu.domain.CoachNameGroup;
import menu.domain.HateMenu;
import menu.domain.MenuSystem;
import menu.domain.MenuTypeGroup;
import menu.dto.MenuResult;
import menu.util.LoopTemplate;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    private final InputView inputView;
    private final OutputView outputView;

    public MenuController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printIntro();
        final CoachNameGroup coachNameGroup = requestCoachNameGroup();
        final List<Coach> coaches = createCoaches(coachNameGroup);
        final MenuSystem menuSystem = new MenuSystem(coaches, new MenuTypeGroup(new ArrayList<>()));
        menuSystem.recommend();
        responseResult(menuSystem);
    }

    private void responseResult(final MenuSystem menuSystem) {
        final List<String> menuTypes = menuSystem.getMenuTypes();
        final List<MenuResult> menuResults = menuSystem.getCoaches().stream()
                .map(MenuResult::of)
                .toList();
        outputView.printResult(menuTypes, menuResults);
    }

    private CoachNameGroup requestCoachNameGroup() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskCoachesName();
            final List<CoachName> coachNames = inputView.readInputs().stream()
                    .map(CoachName::new)
                    .toList();
            return new CoachNameGroup(coachNames);
        });
    }

    private HateMenu requestHateMenu(final String name) {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskHateMenu(name);
            final List<String> inputs = inputView.readInputs();
            return new HateMenu(inputs);
        });
    }

    private List<Coach> createCoaches(final CoachNameGroup coachNameGroup) {
        return coachNameGroup.getCoachNames().stream()
                .map(this::createCoach)
                .toList();
    }

    private Coach createCoach(final CoachName coachName) {
        final HateMenu hateMenu = requestHateMenu(coachName.getValue());
        return new Coach(coachName, hateMenu, new ArrayList<>());
    }
}
