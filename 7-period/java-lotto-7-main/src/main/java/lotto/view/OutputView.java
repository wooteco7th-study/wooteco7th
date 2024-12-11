package lotto.view;

import java.util.stream.Collectors;
import lotto.dto.LottoReceiptGroup;
import lotto.dto.WinningReceiptGroup;
import lotto.dto.WinningReceiptGroup.WinningReceipt;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_MONEY = "구입금액을 입력해 주세요.";
    private static final String LOTTOS_HEADER = "%d개를 구매했습니다.";
    private static final String ASK_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String WINNING_RESULT_HEADER = "당첨 통계";
    private static final String WINNING_RESULT_LINE = "---";
    private static final String WINNING_WITHOUT_BONUS = "%d개 일치 (%,d원) - %d개";
    private static final String WINNING_WITH_BONUS = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
    private static final String WINNING_RATIO = "총 수익률은 %,.1f%%입니다.";

    public void printAskMoney() {
        printlnMessage(ASK_MONEY);
    }

    public void printLottoReceiptGroup(final LottoReceiptGroup lottoReceiptGroup) {
        final String message = lottoReceiptGroup.lottoReceipts().stream()
                .map(lottoReceipt -> lottoReceipt.numbers().toString())
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(
                LINE_SEPARATOR + String.format(LOTTOS_HEADER, lottoReceiptGroup.lottoReceipts().size()) + LINE_SEPARATOR
                        + message);
    }

    public void printAskWinningNumber() {
        printlnMessage(LINE_SEPARATOR + ASK_WINNING_NUMBER);
    }

    public void printAskBonusNumber() {
        printlnMessage(LINE_SEPARATOR + ASK_BONUS_NUMBER);
    }

    public void printWinningResultReceipts(final WinningReceiptGroup winningReceiptGroup) {
        final String message = winningReceiptGroup.winningReceipts().stream()
                .map(this::createWinningResultMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + WINNING_RESULT_HEADER + LINE_SEPARATOR + WINNING_RESULT_LINE + LINE_SEPARATOR
                + message);
    }

    public void printWinningRatio(final double ratio) {
        printMessage(String.format(WINNING_RATIO, ratio));
    }

    private String createWinningResultMessage(final WinningReceipt winningReceipt) {
        String format = WINNING_WITHOUT_BONUS;
        if (winningReceipt.rank() == 2) {
            format = WINNING_WITH_BONUS;
        }
        return String.format(format, winningReceipt.matchNumberCount(), winningReceipt.price(),
                winningReceipt.matchCount());
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
