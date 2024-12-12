package vendingmachine.view;

public class OutputView {

    public static void printMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    public void print(String input) {
        System.out.println(input);
    }

    public void printlnMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }
}
