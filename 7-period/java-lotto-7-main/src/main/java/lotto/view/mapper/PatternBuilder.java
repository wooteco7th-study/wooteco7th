package lotto.view.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PatternBuilder {
    private String wrapperStart = "";
    private String wrapperEnd = "";
    private String itemDelimiter = ";";
    private String dataDelimiter = ",";
    private List<DataType> dataTypes = new ArrayList<>();

    public enum DataType {
        STRING("[가-힣a-zA-Z0-9]+"),
        INTEGER("\\d+"),
        DOUBLE("\\d+(\\.\\d+)?"),
        LOCAL_DATE("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])"),
        POSITIVE_INTEGER("[1-9]\\d*");


        private final String pattern;

        DataType(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

    public PatternBuilder setWrapper(String start, String end) {
        this.wrapperStart = Pattern.quote(start);
        this.wrapperEnd = Pattern.quote(end);
        return this;
    }

    public PatternBuilder setItemDelimiter(String delimiter) {
        this.itemDelimiter = Pattern.quote(delimiter);
        return this;
    }

    public PatternBuilder setDataDelimiter(String delimiter) {
        this.dataDelimiter = Pattern.quote(delimiter);
        return this;
    }

    public PatternBuilder addDataType(DataType type) {
        dataTypes.add(type);
        return this;
    }

    public String build() {
        if (dataTypes.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 최소 하나의 데이터 타입이 필요합니다.");
        }
        StringBuilder patternBuilder = new StringBuilder("^");
        String itemPattern = createItemPattern();
        patternBuilder.append(itemPattern);
        if (!itemDelimiter.isEmpty()) {
            patternBuilder.append("(?:")
                    .append(itemDelimiter)
                    .append(itemPattern)
                    .append(")*");
        }
        patternBuilder.append("$");
        return patternBuilder.toString();
    }

    private String createItemPattern() {
        StringBuilder itemPattern = new StringBuilder();
        itemPattern.append(wrapperStart).append("\\s*");
        for (int i = 0; i < dataTypes.size(); i++) {
            if (i > 0) {
                itemPattern.append("\\s*").append(dataDelimiter).append("\\s*");
            }
            itemPattern.append(dataTypes.get(i).getPattern());
        }
        itemPattern.append("\\s*").append(wrapperEnd);
        return itemPattern.toString();
    }
}
