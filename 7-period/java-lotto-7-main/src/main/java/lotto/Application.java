package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.controller.LottoController;
import lotto.exception.ExceptionHandler;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(inputView, outputView, exceptionHandler, lottoService);
        try {
            lottoController.process();
        } finally {
            Console.close();
        }
    }
}
