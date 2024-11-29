package baseball.controller;

import baseball.constant.GameCommand;
import baseball.constant.ScoreType;
import baseball.domain.BallStrikeChecker;
import baseball.domain.BaseBallGame;
import baseball.domain.ComputerNumber;
import baseball.domain.GameScore;
import baseball.domain.UserNumber;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.Objects;

public class BaseBallController {

    private final InputView inputView;
    private final OutputView outputView;

    public BaseBallController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BaseBallGame baseBallGame = new BaseBallGame();
        do {
            baseBallGame.updateGameScore(new GameScore(0, 0));
            outputView.printIntro();
            start(baseBallGame);
        } while (isRestartGame());
    }

    private void start(final BaseBallGame baseBallGame) {
        final ComputerNumber computerNumber = baseBallGame.generateComputerNumber();
        do {
            final UserNumber userNumber = requestUserNumber();
            final BallStrikeChecker ballStrikeChecker = new BallStrikeChecker(computerNumber, userNumber);
            final int ball = ballStrikeChecker.calculateBall();
            final int strike = ballStrikeChecker.calculateStrike();
            baseBallGame.updateGameScore(new GameScore(ball, strike));
            final ScoreType scoreType = baseBallGame.getScoreType();
            responseScore(scoreType, ball, strike);
        } while (!baseBallGame.isAllStrike());
    }

    private void responseScore(final ScoreType scoreType, final int ball, final int strike) {
        if (Objects.equals(scoreType, ScoreType.BALL)) {
            outputView.printBallCount(ball);
            return;
        }
        if (Objects.equals(scoreType, ScoreType.BALL_AND_STRIKE)) {
            outputView.printBallAndStrikeCount(ball, strike);
            return;
        }
        if (Objects.equals(scoreType, ScoreType.STRIKE)) {
            outputView.printStrikeCount(strike);
            return;
        }
        outputView.printNothing();
    }

    private UserNumber requestUserNumber() {
        outputView.printAskNumbers();
        return new UserNumber(inputView.readNumbers());
    }

    private boolean isRestartGame() {
        final GameCommand gameCommand = requestGameCommand();
        return gameCommand == GameCommand.RESTART;
    }

    private GameCommand requestGameCommand() {
        outputView.printGameClear();
        outputView.printAskGameCommand();
        return inputView.readGameCommand();
    }
}
