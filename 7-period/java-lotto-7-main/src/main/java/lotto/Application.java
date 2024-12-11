package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.controller.LottoController;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.exception.ExceptionHandler;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = createController();
        try {
            lottoController.process();
        } finally {
            Console.close();
        }
    }

    private static LottoController createController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        LottoService lottoService = new LottoService();
        LottoGenerator lottoGenerator = new LottoGenerator(new LottoNumberGenerator());
        return new LottoController(inputView, outputView, exceptionHandler, lottoService,
                lottoGenerator);
    }
}
