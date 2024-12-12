package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import pairmatching.controller.PairController;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.pair.PairHistory;
import pairmatching.exception.ExceptionHandler;
import pairmatching.service.PairService;
import pairmatching.support.Initializer;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {

    public static void main(String[] args) {
        PairController controller = makeController();
        try {
            controller.process();
        } finally {
            Console.close();
        }
    }

    private static PairController makeController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler exceptionHandler = new ExceptionHandler(outputView);
        PairService pairService = makeService();
        return new PairController(inputView, outputView, exceptionHandler,
                pairService);
    }

    private static PairService makeService() {
        Initializer initializer = new Initializer();
        Crews backendCrews = initializer.makeBackendCrews();
        Crews frontendCrews = initializer.makeFrontendCrews();
        PairHistory pairHistory = new PairHistory(new HashMap<>());
        return new PairService(backendCrews, frontendCrews, pairHistory);
    }
}
