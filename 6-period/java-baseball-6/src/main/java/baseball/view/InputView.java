package baseball.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    private String inputUser() {
        return Console.readLine();
    }

    public String inputBaseball() {
        outputView.printMessage(PrintMessage.INPUT_BALL_MESSAGE);
        return inputUser();
    }
}
