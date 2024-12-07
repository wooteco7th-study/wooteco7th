package menu.view.mapper;

import menu.view.mapper.PatternBuilder.DataType;

public class RequestPattern {
    private RequestPattern() {
    }
    
    public static String createItemListPattern() {
        return new PatternBuilder()
                .setItemDelimiter(",")
                .addDataType(DataType.STRING)
                .build();
    }

}
