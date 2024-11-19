package baseball.view;

import baseball.domain.HitRecord;

public class OutputView {

    public void printlnMessage(PrintMessage printMessage) {
        System.out.println(printMessage.getMessage());
    }

    public void printMessage(PrintMessage printMessage) {
        System.out.print(printMessage.getMessage());
    }

    public void printResult(HitRecord result) {
        System.out.println(result.toString());
    }
}
