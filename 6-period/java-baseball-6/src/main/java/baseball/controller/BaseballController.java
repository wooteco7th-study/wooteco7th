package baseball.controller;

import baseball.domain.Computer;
import baseball.domain.HitRecord;
import baseball.domain.Hitter;
import baseball.domain.Reset;
import baseball.service.HitRecordGenerator;
import baseball.service.HitterBallGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;
import baseball.view.PrintMessage;

public class BaseballController {

    private final InputView inputView;
    private final OutputView outputView;

    public BaseballController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printlnMessage(PrintMessage.START_MESSAGE);
        do {
            startInning();
        } while (end());
    }

    private void startInning() {
        Computer computer = new Computer();

        while (true) {
            HitterBallGenerator hitterBallGenerator = new HitterBallGenerator(inputView.inputBaseball());
            Hitter hitter = new Hitter(hitterBallGenerator.getHitterBalls());

            HitRecordGenerator hitRecordGenerator = new HitRecordGenerator(computer.getBalls());
            HitRecord hitRecord = hitRecordGenerator.generate(hitter.getHitterBalls());
            System.out.println(hitRecord.toString());

            if (hitRecord.isThreeStrike()) {
                break;
            }
        }
        outputView.printlnMessage(PrintMessage.END_MESSAGE);
    }

    private boolean end() {
        Reset reset = new Reset(inputView.inputReset());
        return reset.resetGame();
    }
}
