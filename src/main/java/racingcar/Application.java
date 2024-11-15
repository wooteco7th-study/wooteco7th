package racingcar;

import racingcar.game.GameController;
import racingcar.game.InputView;
import racingcar.game.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        new GameController(
                new InputView(),
                new OutputView()
        ).run();
    }
}
