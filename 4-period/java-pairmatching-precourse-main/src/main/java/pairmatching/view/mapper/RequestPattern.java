package pairmatching.view.mapper;

import pairmatching.view.mapper.PatternBuilder.DataType;

public class RequestPattern {
    private RequestPattern() {
    }

    public static String createOptionPattern() {
        return new PatternBuilder()
                .addDataType(DataType.STRING)
                .build();
    }

    public static String createPairMatchDtoPattern() {
        return new PatternBuilder()
                .setItemDelimiter(",")
                .addDataType(DataType.STRING)
                .build();
    }

}
