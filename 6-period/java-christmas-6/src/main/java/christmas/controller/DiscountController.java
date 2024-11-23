package christmas.controller;

import christmas.Domain.Badge;
import christmas.Domain.Orders;
import christmas.Domain.VisitDate;
import christmas.Domain.Visitor;
import christmas.service.Calculator;
import christmas.service.ChristmasDiscounter;
import christmas.service.FreeDiscounter;
import christmas.service.SpecialDiscounter;
import christmas.service.WeekDiscounter;
import christmas.view.OutputView;
import christmas.view.PrintMessage;

public class DiscountController {

    private final OutputView outputView;

    public DiscountController(OutputView outputView) {
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
        showResult(visitor, freeDiscounter);
    }

    private int showDetail(Visitor visitor, FreeDiscounter freeDiscounter) {
        int discount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (visitor.getTotalPrice() >= 10000) {
            ChristmasDiscounter christmasDiscounter = new ChristmasDiscounter(visitor);
            WeekDiscounter weekDiscounter = new WeekDiscounter(visitor);
            SpecialDiscounter specialDiscounter = new SpecialDiscounter(visitor);

            discount += christmasDiscounter.calculate();
            discount += weekDiscounter.calculate();
            discount += specialDiscounter.calculate();

            stringBuilder.append(christmasDiscounter.getMessage());
            stringBuilder.append(weekDiscounter.getMessage());
            stringBuilder.append(specialDiscounter.getMessage());
            stringBuilder.append(freeDiscounter.getMessage());
        }

        if (stringBuilder.isEmpty()) {
            stringBuilder.append("없음");
        }
        outputView.print(stringBuilder.toString());
        return discount;
    }

    private void showResult(Visitor visitor, FreeDiscounter freeDiscounter) {
        int discount = showDetail(visitor, freeDiscounter);

        Calculator calculator = new Calculator(visitor, discount);
        outputView.printMessage(PrintMessage.TOTAL_PROMOTION_INFO_MESSAGE);
        outputView.printMoney(calculator.getTotalDiscount(freeDiscounter.calculate()));

        outputView.printMessage(PrintMessage.PAYMENT_INFO_MESSAGE);
        outputView.printMoney(calculator.getTotalPayment());

        outputView.printMessage(PrintMessage.BADGE_INFO_MESSAGE);
        outputView.print(Badge.getBadge(calculator.getTotalDiscountToBadge(freeDiscounter.calculate())));
    }
}
