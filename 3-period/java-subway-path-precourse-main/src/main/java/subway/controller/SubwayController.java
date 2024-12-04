package subway.controller;

import subway.service.MainValidator;
import subway.view.InputView;

public class SubwayController {

    private final InputView inputView;

    public SubwayController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        MainValidator mainValidator = new MainValidator(inputView.getMain());
        if (mainValidator.isTrue()) {
            //다음 동작 수행
        }
        //아니라면 종료
    }
}
