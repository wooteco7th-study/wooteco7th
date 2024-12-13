package christmas.domain.controller;

import christmas.domain.EventPlanner;
import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;
import christmas.dto.DiscountReceiptGroup;
import christmas.dto.OrderMenuReceiptGroup;
import christmas.util.LoopTemplate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printIntro();
        final VisitDate visitDate = requestVisitDate();
        final OrderMenuGroup orderMenuGroup = requestOrderMenuGroup();
        final EventPlanner eventPlanner = EventPlanner.of(visitDate, orderMenuGroup);
        outputView.printOrderMenus(OrderMenuReceiptGroup.of(orderMenuGroup).orderMenuReceipts());
        response(eventPlanner);
    }

    private void response(final EventPlanner eventPlanner) {
        outputView.printTotalPriceBeforeDiscount(eventPlanner.calculateBeforeDiscountTotalPrice());
        outputView.printBenefitMenu(eventPlanner.getBenefitMenu().getName(), eventPlanner.getBenefitQuantity());
        outputView.printDiscountReceipts(DiscountReceiptGroup.of(eventPlanner.getCanReceiveDiscount()).discountReceipts());
        outputView.printTotalDiscount(eventPlanner.calculateTotalDiscount());
        outputView.printTotalPriceAfterDiscount(eventPlanner.calculateAfterDiscountTotalPrice());
        outputView.printBadge(eventPlanner.getBadge().getName());
    }

    private VisitDate requestVisitDate() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskVisitDate();
            return inputView.readVisitDate();
        });
    }

    private OrderMenuGroup requestOrderMenuGroup() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskOrderMenus();
            final List<String> orderMenus = inputView.readOrderMenus();
            return OrderMenuGroup.of(orderMenus);
        });
    }
}
