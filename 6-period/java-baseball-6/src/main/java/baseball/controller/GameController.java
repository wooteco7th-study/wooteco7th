package baseball.controller;

import baseball.domain.GameCommand;
import baseball.domain.NumberComparator;
import baseball.domain.Numbers;
import baseball.domain.RandomNumbersGenerator;
import baseball.dto.ResultResponse;
import baseball.service.GameService;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.List;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStartMsg();
        executeGame();
        String restartOrNot = inputView.readRestartOrNot();
        GameCommand gameCommand = GameCommand.from(restartOrNot);
        if (gameCommand.equals(GameCommand.RESTART)) {
            executeGame();
        }
    }

    private void executeGame() {
        RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();
        Numbers computerNumbers = randomNumbersGenerator.generate();
//        System.out.println("computerNumbers.getNumbers() = " + computerNumbers.getNumbers());
        List<Integer> numbers = inputView.readNumbers();
        Numbers userNumbers = new Numbers(numbers);
        NumberComparator numberComparator = new NumberComparator();
        while (!numberComparator.isThreeStrike(userNumbers, computerNumbers)) {
            ResultResponse resultResponse = numberComparator.compare(userNumbers, computerNumbers);
            outputView.printResult(resultResponse);
            userNumbers = new Numbers(inputView.readNumbers());
        }
        if (numberComparator.isThreeStrike(userNumbers, computerNumbers)) {
            ResultResponse resultResponse = numberComparator.compare(userNumbers, computerNumbers);
            outputView.printResult(resultResponse);
            outputView.printEndMsg();
        }
    }
}
