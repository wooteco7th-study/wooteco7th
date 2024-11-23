package christmas.view;

public class OutputView {

    public void printMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    public void printVisitDate(PrintMessage printMessage, int visitDate) {
        System.out.println(String.format(printMessage.getMessage(), visitDate));
    }
}
