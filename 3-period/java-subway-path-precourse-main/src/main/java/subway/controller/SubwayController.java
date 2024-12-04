package subway.controller;

import java.util.List;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Result;
import subway.service.MainValidator;
import subway.service.StandardValidator;
import subway.service.SubwayCreator;
import subway.service.SubwayValidator;
import subway.service.WayFinder;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final SubwayCreator subwayCreator = new SubwayCreator();

    public SubwayController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        while (true) {
            MainValidator mainValidator = new MainValidator(inputView.getMain());
            if (mainValidator.isNotTrue()) {
                break;
            }

            StandardValidator standardValidator = new StandardValidator(inputView.getWayStandard());
            if (standardValidator.isBack()) {
                continue;
            }

            String start = inputView.getStartSubway();
            String end = inputView.getEndSubway();
            SubwayValidator subwayValidator = new SubwayValidator(start, end);

            WayFinder wayFinder = new WayFinder(standardValidator.isOne());
            Result result = wayFinder.find(start, end);
            OutputView.printResult(result.toString());
        }
    }
}
