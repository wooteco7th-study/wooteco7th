package christmas;

import christmas.Domain.Orders;
import christmas.Domain.VisitDate;
import christmas.controller.DiscountController;
import christmas.controller.OrdersController;
import christmas.controller.VisitDateController;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.PrintMessage;

public class Application {
    public static void main(String[] args) {
        final OutputView outputView = new OutputView();
        final InputView inputView = new InputView(outputView);

        outputView.printMessage(PrintMessage.HELLO_MESSAGE);
        VisitDateController visitDateController = new VisitDateController(inputView);
        VisitDate visitDate = visitDateController.run();

        OrdersController ordersController = new OrdersController(inputView, outputView);
        Orders orders = ordersController.run();

        DiscountController discountController = new DiscountController(outputView);
        discountController.run(visitDate, orders);
    }
}
