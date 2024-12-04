package subway.controller;

import subway.service.MainValidator;
import subway.service.StandardValidator;
import subway.view.InputView;

public class SubwayController {

    private final InputView inputView;

    public SubwayController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        while (true) {
            MainValidator mainValidator = new MainValidator(inputView.getMain());
            if (mainValidator.isNotTrue()) {
                break;
            }
            while (true) {
                StandardValidator standardValidator = new StandardValidator(inputView.getWayStandard());
                if (standardValidator.isBack()) {
                    break;
                }
                // 다음 동작 수행
            }
        }
    }
}
