package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderGroup;
import christmas.domain.VisitDate;
import christmas.service.ChristmasService;
import christmas.util.LoopTemplate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(final InputView inputView, final OutputView outputView,
                               final ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public void run() {
        requestVisitDate();
        requestOrderMenus();
    }

    private VisitDate requestVisitDate() {
        return LoopTemplate.tryCatchLoop(() ->{
            outputView.printAskVisitDay();
            final int day = inputView.readVisitDay();
            return VisitDate.of(day);
        });
    }

    private OrderGroup requestOrderMenus() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskOrderMenu();
            final List<String> orderMenus = inputView.readOrderMenu();
            final List<Order> orders = christmasService.generateOrders(orderMenus);
            return new OrderGroup(orders);
        });
    }

}
