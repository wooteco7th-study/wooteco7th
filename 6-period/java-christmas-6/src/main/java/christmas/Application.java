package christmas;

import christmas.Domain.VisitDate;
import christmas.service.Converter;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.PrintMessage;

public class Application {
    public static void main(String[] args) {
        final OutputView outputView = new OutputView();
        final InputView inputView = new InputView(outputView);

        outputView.printMessage(PrintMessage.HELLO_MESSAGE);
        VisitDate visitDate = new VisitDate(Converter.getNumber(inputView.getVisitDate()));
    }
}
