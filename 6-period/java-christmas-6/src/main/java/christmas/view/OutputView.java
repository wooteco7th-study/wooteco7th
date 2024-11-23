package christmas.view;

import christmas.Domain.Visitor;

public class OutputView {

    public void printMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    public void printVisitDate(PrintMessage printMessage, int visitDate) {
        System.out.println(String.format(printMessage.getMessage(), visitDate));
    }

    public void printOrderDetail(Visitor visitor) {
        System.out.println(visitor.toString());
    }

    public void printTotalPrice(Visitor visitor) {
        System.out.println(visitor.getPriceMessage());
    }

    public void print(String input) {
        System.out.println(input);
    }

    public void printExpectPayment(int total, int discount) {
        System.out.println(String.format("%,d원", total - discount));
    }
}
