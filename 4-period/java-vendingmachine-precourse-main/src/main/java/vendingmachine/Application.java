package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.controller.VendingController;
import vendingmachine.exception.ExceptionHandler;
import vendingmachine.price.coin.CoinGenerator;
import vendingmachine.price.coin.RandomCoinGenerator;
import vendingmachine.service.VendingService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionHandler handler = new ExceptionHandler(outputView);
        CoinGenerator randomCoinGenerator = new RandomCoinGenerator();
        VendingService service = new VendingService(randomCoinGenerator);
        VendingController vendingController = new VendingController(inputView, outputView, handler, service);
        try {
            vendingController.process();
        } finally {
            Console.close();
        }
    }
}
