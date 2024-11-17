package lotto.view.console;

import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.LottoNumber;
import lotto.dto.WinningLottoRecipe;
import lotto.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_MONEY = "구입금액을 입력해 주세요.";
    private static final String LOTTO_NUMBERS_HEADER = "%d개를 구매했습니다.";
    private static final String ASK_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String PRIZE_RESULT_HEADER = "당첨 통계";
    private static final String PRIZE_RESULT_LINE = "---";
    private static final String WINNING_LOTTO_WITHOUT_BONUS = "%d개 일치 (%,d원) - %d개";
    private static final String WINNING_LOTTO_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
    private static final String PRIZE_RESULT_RATIO = "총 수익률은 %,.1f%%입니다.";


    @Override
    public void printAskMoney() {
        printlnMessage(ASK_MONEY);
    }

    @Override
    public void printLottoNumbers(final List<LottoNumber> lottoNumbers) {
        final String message = lottoNumbers.stream()
                .map(Record::toString)
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(
                LINE_SEPARATOR + String.format(LOTTO_NUMBERS_HEADER, lottoNumbers.size()) + LINE_SEPARATOR + message);
    }

    @Override
    public void printAskWinningNumber() {
        printlnMessage(LINE_SEPARATOR + ASK_WINNING_NUMBER);
    }

    @Override
    public void printAskBonusNumber() {
        printlnMessage(LINE_SEPARATOR + ASK_BONUS_NUMBER);
    }

    @Override
    public void printWinningLottoRecipes(final List<WinningLottoRecipe> winningLottoRecipes) {
        final String message = winningLottoRecipes.stream()
                .map(this::createWinningLottoRecipeMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
        final String prizeResultTitle =
                LINE_SEPARATOR + PRIZE_RESULT_HEADER + LINE_SEPARATOR + PRIZE_RESULT_LINE + LINE_SEPARATOR;
        printlnMessage(prizeResultTitle + message);
    }

    @Override
    public void printPrizeResultRatio(final double prizeResultRatio) {
        printMessage(String.format(PRIZE_RESULT_RATIO, prizeResultRatio));
    }

    private String createWinningLottoRecipeMessage(final WinningLottoRecipe winningLottoRecipe) {
        final int rank = winningLottoRecipe.rank();
        String format = WINNING_LOTTO_WITHOUT_BONUS;
        if (rank == 2) {
            format = WINNING_LOTTO_WITH_BONUS;
        }
        return String.format(format, winningLottoRecipe.matchNumberCount(), winningLottoRecipe.prizePrice(),
                winningLottoRecipe.prizeCount());
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
