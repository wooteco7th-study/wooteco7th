package christmas;

import christmas.Domain.Order;
import christmas.Domain.Orders;
import christmas.Domain.VisitDate;
import christmas.Domain.Visitor;
import christmas.service.Converter;
import christmas.service.OrderCreator;
import christmas.service.Separator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.PrintMessage;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        final OutputView outputView = new OutputView();
        final InputView inputView = new InputView(outputView);

        outputView.printMessage(PrintMessage.HELLO_MESSAGE);
        VisitDate visitDate = new VisitDate(Converter.getNumber(inputView.getVisitDate()));

        outputView.printVisitDate(PrintMessage.EVENT_DETAIL_INFO_MESSAGE, visitDate.visitDate());
        String userOrders = inputView.getOrders();
        List<String> splitOrders = Separator.separate(userOrders, ",");
        List<Order> order = splitOrders.stream()
                .map(split -> new OrderCreator(split).create())
                .toList();
        Orders orders = new Orders(order);

        Visitor visitor = new Visitor(visitDate, orders);
    }
}
