package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitDate;
import christmas.error.ErrorMessage;
import christmas.util.StringParser;
import java.util.List;

public class InputView {

    private static final String DELIMITER_COMMA = ",";
    private static final String SPACE = " ";

    public VisitDate readVisitDate() {
        final int dayOfMonth = StringParser.parseToNumber(readInput(), ErrorMessage.VISIT_DAY_FORMAT);
        return VisitDate.of(dayOfMonth);
    }

    public List<String> readOrderMenus() {
        return StringParser.parseToTokens(readInput().strip(), DELIMITER_COMMA);
    }

    private String readInput() {
        return Console.readLine();
    }

}
