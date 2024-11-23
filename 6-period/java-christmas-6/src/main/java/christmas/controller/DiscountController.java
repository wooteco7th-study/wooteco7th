package christmas.controller;

import christmas.Domain.Orders;
import christmas.Domain.VisitDate;
import christmas.Domain.Visitor;
import christmas.service.ChristmasDiscounter;
import christmas.service.FreeDiscounter;
import christmas.service.SpecialDiscounter;
import christmas.service.WeekDiscounter;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.PrintMessage;

public class DiscountController {

    private final InputView inputView;
    private final OutputView outputView;

    public DiscountController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(VisitDate visitDate, Orders orders) {
        Visitor visitor = new Visitor(visitDate, orders);
        outputView.printVisitDate(PrintMessage.EVENT_DETAIL_INFO_MESSAGE, visitor.getWillVisitDate());

        outputView.printMessage(PrintMessage.ORDER_MENU_INFO_MESSAGE);
        outputView.printOrderDetail(visitor);

        outputView.printMessage(PrintMessage.TOTAL_PRICE_INFO_MESSAGE);
        outputView.printTotalPrice(visitor);

        outputView.printMessage(PrintMessage.GIVE_MENU_INFO_MESSAGE);
        FreeDiscounter freeDiscounter = new FreeDiscounter(visitor);
        outputView.print(freeDiscounter.toString());

        outputView.printMessage(PrintMessage.PROMOTION_INFO_MESSAGE);
        int discount = 0;
        StringBuilder stringBuilder = new StringBuilder();

        if (visitor.getTotalPrice() >= 10000) {
            ChristmasDiscounter christmasDiscounter = new ChristmasDiscounter(visitor);
            WeekDiscounter weekDiscounter = new WeekDiscounter(visitor);
            SpecialDiscounter specialDiscounter = new SpecialDiscounter(visitor);

            discount += christmasDiscounter.calculate();
            discount += weekDiscounter.calculate();
            discount += freeDiscounter.calculate();
            discount += specialDiscounter.calculate();

            stringBuilder.append(christmasDiscounter.getMessage()).append("\n");
            stringBuilder.append(weekDiscounter.getMessage()).append("\n");
            stringBuilder.append(specialDiscounter.getMessage()).append("\n");
            stringBuilder.append(freeDiscounter.getMessage()).append("\n");
        }

        if (stringBuilder.isEmpty()) {
            stringBuilder.append("없음");
        }
        outputView.print(stringBuilder.toString());

        outputView.printMessage(PrintMessage.PAYMENT_INFO_MESSAGE);
        outputView.printExpectPayment(visitor.getTotalPrice(), discount);
    }
}
