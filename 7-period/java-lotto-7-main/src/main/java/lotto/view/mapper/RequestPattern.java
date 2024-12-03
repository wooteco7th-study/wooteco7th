package lotto.view.mapper;

import lotto.view.mapper.PatternBuilder.DataType;

public class RequestPattern {
    private RequestPattern() {
    }

    public static String createPurchaseAmountPattern() {
        return new PatternBuilder()
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    public static String createWinningNumbersPattern() {
        return new PatternBuilder()
                .setItemDelimiter(",")
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

    public static String createBonusNumberPattern() {
        return new PatternBuilder()
                .addDataType(DataType.POSITIVE_INTEGER)
                .build();
    }

}
