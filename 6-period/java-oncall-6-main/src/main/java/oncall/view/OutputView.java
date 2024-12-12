package oncall.view;

import java.util.List;
import oncall.dto.TurnDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String REQUEST_START_DAY = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String REQUEST_WEEKDAY_TURN = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String REQUEST_WEEKEND_TURN = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String RESULT_FORMAT = "%d월 %d일 %s%s %s";
    private static final String HOLIDAY = "(휴일)";
    private static final String EMPTY = "";

    public void showRequestStartDay() {
        show(REQUEST_START_DAY);
    }

    public void showRequestWeekdayTurn() {
        show(REQUEST_WEEKDAY_TURN);
    }

    public void showRequestHolidayTurn() {
        show(REQUEST_WEEKEND_TURN);
    }

    public void showResult(List<TurnDto> dtos) {
        showln("");
        dtos.stream()
                .map(this::makeMessage)
                .forEach(this::showln);
    }

    private String makeMessage(final TurnDto dto) {
        if (dto.isSpecialDay()) {
            return String.format(RESULT_FORMAT, dto.monthNumber(), dto.dayNumber(), dto.day(), HOLIDAY, dto.name());
        }
        return String.format(RESULT_FORMAT, dto.monthNumber(), dto.dayNumber(), dto.day(), EMPTY, dto.name());
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

    private void show(String message) {
        System.out.print(message);
    }
}
