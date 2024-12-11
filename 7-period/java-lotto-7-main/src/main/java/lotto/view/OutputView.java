package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.StatisticsDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String REQUEST_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String INFORM_QUANTITY = "%d개를 구매했습니다.";
    private static final String REQUEST_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String INFORM_RESULT = """
            당첨 통계
            ---
            3개 일치 (5,000원) - %d개
            4개 일치 (50,000원) - %d개
            5개 일치 (1,500,000원) - %d개
            5개 일치, 보너스 볼 일치 (30,000,000원) - %d개
            6개 일치 (2,000,000,000원) - %d개""";
    private static final String INFORM_PROFIT = "총 수익률은 %.2f%입니다.";
    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    public void showRequestAmount() {
        showln(REQUEST_AMOUNT);
    }

    public void showPurchaseLotto(final int quantity, final List<List<Integer>> numbers) {
        showln(LINE + format(INFORM_QUANTITY, quantity));
        numbers.stream()
                .map(this::makeLottoMessage)
                .forEach(this::showln);
    }

    public void showRequestWinningNumber() {
        showln(LINE + REQUEST_WINNING_NUMBER);
    }

    public void showRequestBonusNumber() {
        showln(LINE + REQUEST_BONUS_NUMBER);
    }

    public void showInformResult(final StatisticsDto dto, double profit) {
        showln(format(INFORM_RESULT, dto.match3(), dto.match4(), dto.match5(), dto.match5WithBonus(), dto.match6()));
        showln(format(INFORM_PROFIT, profit));
    }

    private String makeLottoMessage(final List<Integer> numbers) {
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
