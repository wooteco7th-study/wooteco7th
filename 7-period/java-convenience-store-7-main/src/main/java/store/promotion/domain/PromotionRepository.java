package store.promotion.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import store.error.ErrorMessage;
import store.util.FileReader;
import store.util.StringParser;

public class PromotionRepository {

    private static final String PATH = "promotions.md";
    private final List<Promotion> promotions;

    public PromotionRepository(final List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion findByName(final String name) {
        return promotions.stream()
                .filter(promotion -> Objects.equals(promotion.getName(), name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(ErrorMessage.INVALID_NOT_FOUND_PROMOTION.getMessage()));
    }


    public void initialize() {
        final Queue<String> fileLines = FileReader.readFileLines(PATH);
        fileLines.poll();
        while (!fileLines.isEmpty()) {
            final String line = fileLines.poll();
            promotions.add(createPromotion(line));
        }
    }

    private Promotion createPromotion(final String line) {
        final String[] splitLine = line.split(",");
        final String name = splitLine[0];
        final int buy = StringParser.parseToInt(splitLine[1], ErrorMessage.INVALID_NUMBER_FORMAT);
        final int get = StringParser.parseToInt(splitLine[2], ErrorMessage.INVALID_NUMBER_FORMAT);
        final LocalDate start = convertToLocalDate(splitLine[3]);
        final LocalDate end = convertToLocalDate(splitLine[4]);
        return new Promotion(name, buy, get, start, end);
    }

    private LocalDate convertToLocalDate(final String date) {
        final String[] splitDate = date.split("-");
        final int year = StringParser.parseToInt(splitDate[0], ErrorMessage.INVALID_NUMBER_FORMAT);
        final int month = StringParser.parseToInt(splitDate[1], ErrorMessage.INVALID_NUMBER_FORMAT);
        final int dayOfMonth = StringParser.parseToInt(splitDate[2], ErrorMessage.INVALID_NUMBER_FORMAT);
        return LocalDate.of(year, month, dayOfMonth);
    }
}
