package baseball.view;

public class OutputView {

    public void printlnMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    public void printMessage(PrintMessage printMessage) {
        System.out.print(printMessage.getMessage());
    }
}
