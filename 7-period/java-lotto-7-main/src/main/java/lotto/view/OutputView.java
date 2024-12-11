package lotto.view;

import java.util.stream.Collectors;
import lotto.dto.LottoReceiptGroup;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_MONEY = "구입금액을 입력해 주세요.";
    private static final String LOTTOS_HEADER = "%d개를 구매했습니다.";

    public void printAskMoney() {
        printlnMessage(ASK_MONEY);
    }

    public void printLottoReceiptGroup(final LottoReceiptGroup lottoReceiptGroup) {
        final String message = lottoReceiptGroup.lottoReceipts().stream()
                .map(lottoReceipt -> lottoReceipt.numbers().toString())
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + LOTTOS_HEADER + LINE_SEPARATOR + message);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
