package bridge.domain;

import bridge.exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

class BridgeGameTest {

    private BridgeGame bridgeGame;

    @BeforeEach
    void setUp() {
        List<String> bridgeAnswer = List.of("U", "D", "D");
        bridgeGame = new BridgeGame(bridgeAnswer);
    }

    @Test
    void 유저가_입력한_값과_정답이_일치하면_true를_반환한다1() {
        // given
        String userInput = "U";
        bridgeGame.move(userInput);
        // when
        boolean isSame = bridgeGame.compare();
        // then
        assertThat(isSame).isTrue();
    }

    @Test
    void 유저가_입력한_값과_정답이_일치하면_true를_반환한다2() {
        // given
        String firstUserInput = "U";
        bridgeGame.move(firstUserInput);
        String secondUserInput = "D";
        bridgeGame.move(secondUserInput);
        // when
        boolean isSame = bridgeGame.compare();
        // then
        assertThat(isSame).isTrue();
    }

    @Test
    void 유저가_입력한_값과_정답이_일치하지_않으면_false를_반환한다() {
        // given
        String userInput = "D";
        bridgeGame.move(userInput);
        // when
        boolean isSame = bridgeGame.compare();
        // then
        assertThat(isSame).isFalse();
    }

    @Test
    void 정답_사이즈와_유저가_입력한_횟수가_일치하지_않으면_게임을_계속한다() {
        // given
        String firstUserInput = "U";
        bridgeGame.move(firstUserInput);
        String secondUserInput = "D";
        bridgeGame.move(secondUserInput);
        // when
        boolean keepGame = bridgeGame.keepGame();
        // then
        assertThat(keepGame).isTrue();
    }

    @Test
    void 정답_사이즈와_유저가_입력한_횟수가_일치하면_게임을_멈춘다() {
        // given
        String firstUserInput = "U";
        bridgeGame.move(firstUserInput);
        String secondUserInput = "D";
        bridgeGame.move(secondUserInput);
        String lastUserInput = "D";
        bridgeGame.move(lastUserInput);
        // when
        boolean keepGame = bridgeGame.keepGame();
        // then
        assertThat(keepGame).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"T", "P", "R", "Q"})
    void 다리_이동할_칸_관련_명령어_이외_다른_명령어_입력시_예외가_발생한다(String gameCommand) {
        assertThatIllegalArgumentException().isThrownBy(() -> bridgeGame.move(gameCommand))
                .withMessageContaining(
                        ExceptionMessage.COMMAND_NOT_FOUND.getMessage());

    }

    @ParameterizedTest
    @CsvSource(value = {"R,true,2", "Q,false,1"})
    void 재시도_여부에_따라_총_시도_횟수를_계산한다(String gameCommand, boolean expected, int totalTrialCount) {
        // given & when
        boolean retryOrNot = bridgeGame.retry(gameCommand);
        // then
        assertAll(
                () -> assertThat(retryOrNot).isEqualTo(expected),
                () -> assertThat(bridgeGame.getTotalTrialCount()).isEqualTo(totalTrialCount)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"U", "D", "L"})
    void 재시도_관련_명령어_이외_다른_명령어_입력시_예외가_발생한다(String gameCommand) {
        assertThatIllegalArgumentException().isThrownBy(() -> bridgeGame.retry(gameCommand))
                .withMessageContaining(
                        ExceptionMessage.COMMAND_NOT_FOUND.getMessage());

    }
}
