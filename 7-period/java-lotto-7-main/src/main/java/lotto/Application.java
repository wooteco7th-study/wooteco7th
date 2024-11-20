package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.console.ConsoleInputView;
import lotto.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        final InputView consoleInputView = new ConsoleInputView();
        final OutputView consoleOutputView = new ConsoleOutputView();
        final LottoController lottoController = new LottoController(consoleInputView, consoleOutputView);
        lottoController.run();
    }
}
