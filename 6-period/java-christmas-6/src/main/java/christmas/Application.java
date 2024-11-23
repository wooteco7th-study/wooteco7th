package christmas;

import christmas.controller.OrderController;
import christmas.domain.OrderProcessor;
import christmas.domain.discount.DiscountProcessor;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OrderController orderController = new OrderController(new InputView(), new OutputView(), new OrderProcessor(new DiscountProcessor()));
        orderController.run();

    }
}
