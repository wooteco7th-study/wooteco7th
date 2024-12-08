package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.controller.VendingController;
import vendingmachine.domain.price.coin.CoinGenerator;
import vendingmachine.domain.price.coin.RandomCoinGenerator;
import vendingmachine.exception.ExceptionHandler;
import vendingmachine.service.VendingService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {

    public static void main(String[] args) {
        VendingController vendingController = createController();
        try {
            vendingController.process();
        } finally {
            Console.close();
        }
    }

    private static VendingController createController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler handler = new ExceptionHandler(outputView);
        CoinGenerator randomCoinGenerator = new RandomCoinGenerator();
        VendingService service = new VendingService(randomCoinGenerator);
        return new VendingController(inputView, outputView, handler, service);
    }
}
