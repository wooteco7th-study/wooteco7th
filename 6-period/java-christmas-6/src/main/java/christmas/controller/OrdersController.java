package christmas.controller;

import christmas.Domain.Order;
import christmas.Domain.Orders;
import christmas.service.OrderCreator;
import christmas.service.Separator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class OrdersController {

    private final InputView inputView;
    private final OutputView outputView;

    public OrdersController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Orders run() {
        while (true) {
            try {
                return getOrders();
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    private Orders getOrders() {
        String userOrders = inputView.getOrders();
        List<String> splitOrders = Separator.separate(userOrders, ",", 0);
        List<Order> order = splitOrders.stream()
                .map(split -> new OrderCreator(split).create())
                .toList();
        return new Orders(order);
    }
}
