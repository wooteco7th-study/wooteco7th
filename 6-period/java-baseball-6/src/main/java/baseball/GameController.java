package baseball;

import static baseball.InputView.*;
import static baseball.InputView.YESorNO.GAME_RESTART;
import static baseball.OutputView.*;
import static baseball.rule.BaseballRule.MAX_NUMBER;
import static baseball.rule.BaseballRule.MIN_NUMBER;

import baseball.domain.BaseballGame;
import baseball.domain.Computer;
import baseball.domain.User;
import baseball.infrastructure.BaseBallNumberGenerator;

public class GameController {
    private final BaseBallNumberGenerator numberGenerator;

    public GameController() {
        this.numberGenerator = new BaseBallNumberGenerator(
                MIN_NUMBER.getValue(),
                MAX_NUMBER.getValue()
        );
    }

    public void run(){
        boolean isGameRunning = true;

        while (isGameRunning) {
            playOneGame();
            isGameRunning = requestGameContinue();
        }

    }
    private void playOneGame() {
        // given
        Computer computer = new Computer(numberGenerator.pickUniqueThreeNumbers());
        // when
        printStartMessage();
        playUntilGameOver(computer);
    }

    private static void playUntilGameOver(final Computer computer) {
        boolean isCorrect = false;

        while (!isCorrect) {
            printRequestNumberMessage();
            User user = new User(requestUserNumber());
            BaseballGame baseballGame = BaseballGame.of(computer, user);

            printPlayStatus(baseballGame);

            if (baseballGame.isAllStrike()) {
                printEndingMessage();
                isCorrect = true;
            }
        }
    }

    private boolean requestGameContinue() {
        printReplayMessage();
        YESorNO choice = requestRePlayChoice();
        return choice == GAME_RESTART;
    }
}
