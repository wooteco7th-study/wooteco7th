package baseball;

import static baseball.InputView.*;
import static baseball.InputView.YESorNO.GAME_RESTART;
import static baseball.OutputView.*;

import baseball.domain.BaseballGame;
import baseball.domain.Computer;
import baseball.domain.User;

public class GameController {

    public void run(){
        boolean isGameRunning = true;

        while (isGameRunning) {
            playOneGame();
            isGameRunning = requestGameContinue();
        }

    }
    private void playOneGame() {
        // given
        Computer computer = new Computer(NumberGenerator.pickUniqueThreeNumbers());
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
