package baseball;

import static baseball.InputView.*;
import static baseball.InputView.YESorNO.GAME_RESTART;
import static baseball.OutputView.*;

import java.util.List;

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
        List<Integer> computer = NumberGenerator.pickUniqueThreeNumbers();
        // when
        printStartMessage();
        playUntilGameOver(computer);
    }

    private static void playUntilGameOver(final List<Integer> computer) {
        boolean isCorrect = false;

        while (!isCorrect) {
            printRequestNumberMessage();
            List<Integer> user = requestUserNumber();
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
