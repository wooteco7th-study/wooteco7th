package vendingmachine.view;

public class OutputView {

    private static final String LINE = System.lineSeparator();

    public void showExceptionMessage(final String message) {
        showln(message);
    }

    private void showln(final String message) {
        System.out.println(message);
    }
}
