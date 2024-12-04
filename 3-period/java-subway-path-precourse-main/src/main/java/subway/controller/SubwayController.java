package subway.controller;

import subway.view.InputView;

public class SubwayController {

    private final InputView inputView;

    public SubwayController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        String inputMain = inputView.getMain();
        // 입력 검증 및 기능 구현
    }
}
