package baseball;

import baseball.view.InputView;
import baseball.view.OutputView;
import baseball.view.PrintMessage;

public class Application {
    public static void main(String[] args) {

        OutputView outputView = new OutputView();
        InputView inputView = new InputView(outputView);

        outputView.printlnMessage(PrintMessage.START_MESSAGE);
        System.out.println(inputView.inputBaseball());
    }
}
