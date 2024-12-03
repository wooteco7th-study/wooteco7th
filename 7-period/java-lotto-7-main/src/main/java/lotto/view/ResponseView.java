package lotto.view;

import static java.util.stream.Collectors.joining;

import lotto.domain.Winning;
import lotto.domain.vo.Lotto;
import lotto.dto.UserLottosInfo;
import lotto.dto.WinningStatisticsInfo;

public class ResponseView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String LOTTO_QUANTITY_FORMAT = "%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String WINNING_STATISTICS_HEADER = "---";
    private static final String WINNING_STATISTICS_DETAILS = "%d개 일치%s (%,d원) - %d개";

    private String toFormat(String format, Object... args) {
        return String.format(format, args);
    }

    public void responseUserLottosInfo(final UserLottosInfo userLottosInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append(toFormat(LOTTO_QUANTITY_FORMAT, userLottosInfo.getLottoQuantity()))
                .append(NEW_LINE);

        for (Lotto lotto : userLottosInfo.getUserLottosValue()) {
            builder.append("[")
                    .append(lotto.getLottoNumbers().stream()
                            .map(String::valueOf)
                            .collect(joining(", ")))
                    .append("]")
                    .append(NEW_LINE);
        }

        System.out.println(builder.toString());
    }

    public void printWinningStatisticsInfo(final WinningStatisticsInfo winningStatisticsInfo) {
        StringBuilder builder = new StringBuilder();
        builder.append(WINNING_STATISTICS)
                .append(NEW_LINE)
                .append(WINNING_STATISTICS_HEADER)
                .append(NEW_LINE);  // 헤더 다음에 줄바꿈 추가

        winningStatisticsInfo.getWinningInfoRepository()
                .entrySet()
                .stream().filter(winning -> winning.getKey() != Winning.NONE)
                .forEach(winning -> {
                    String bonusText = winning.getKey().getMatchCount() == 5 &&
                            winning.getKey().isHasBonus() ? ", 보너스 볼 일치" : "";

                    builder.append(toFormat(
                                    WINNING_STATISTICS_DETAILS,
                                    winning.getKey().getMatchCount(),
                                    bonusText,
                                    winning.getKey().getPrice(),
                                    winning.getValue().intValue()))
                            .append(NEW_LINE);
                });

        double roundedPercent = Math.round(winningStatisticsInfo.getProfitPercent() * 10.0) / 10.0;
        builder.append("총 수익률은 ")
                .append(String.format("%.1f", roundedPercent))
                .append("%입니다.");

        System.out.println(builder.toString());
    }
}
