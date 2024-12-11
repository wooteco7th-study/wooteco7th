package baseball.controller;

import baseball.domain.GameCommand;
import baseball.domain.NumberComparator;
import baseball.domain.Numbers;
import baseball.domain.NumbersGenerator;
import baseball.dto.ResultResponse;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.List;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final NumbersGenerator numbersGenerator;

    public GameController(final InputView inputView, final OutputView outputView, final NumbersGenerator numbersGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numbersGenerator = numbersGenerator;
    }

    public void run() {
        outputView.printGameStartMsg();
        executeGame();
        restartOrStop();
    }

    private void executeGame() {
        Numbers computerNumbers = numbersGenerator.generate();
        Numbers userNumbers = getUserNumbers();
        NumberComparator numberComparator = new NumberComparator();

        while (!numberComparator.isThreeStrike(userNumbers, computerNumbers)) {
            ResultResponse resultResponse = numberComparator.compare(userNumbers, computerNumbers);
            outputView.printResult(resultResponse);
            userNumbers = new Numbers(inputView.readNumbers());
        }
        // FIX: 계산 반복됨
        ResultResponse resultResponse = numberComparator.compare(userNumbers, computerNumbers);
        outputView.printResult(resultResponse);
        outputView.printEndMsg();
    }

    private Numbers getUserNumbers() {
        List<Integer> numbers = inputView.readNumbers();
        return new Numbers(numbers);
    }

    private void restartOrStop() {
        String restartOrNot = inputView.readRestartOrNot();

        GameCommand gameCommand = GameCommand.from(restartOrNot);
        if (gameCommand.equals(GameCommand.RESTART)) {
            executeGame();
        }
    }
}
