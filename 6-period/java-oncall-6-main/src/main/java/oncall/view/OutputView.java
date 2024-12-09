package oncall.view;

import java.util.List;
import oncall.dto.TurnDto;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String REQUEST_START = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String REQUEST_WEEKDAY = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String REQUEST_HOLIDAY = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String INFORM_WEEKDAY = "%d월 %d일 %s %s";
    private static final String INFORM_HOLIDAY = "%d월 %d일 %s(휴일) %s";

    public void showInformTurn(List<TurnDto> dtos) {
        showln("");
        dtos.stream()
                .map(this::makeMessage)
                .forEach(this::showln);
    }

    private String makeMessage(TurnDto dto) {
        if (dto.isHoliday()) {
            return format(INFORM_HOLIDAY, dto.month(), dto.day(), dto.dayOfWeek(), dto.name());
        }
        return format(INFORM_WEEKDAY, dto.month(), dto.day(), dto.dayOfWeek(), dto.name());
    }

    public void showRequestStart() {
        show(REQUEST_START);
    }

    public void showRequestWeekday() {
        show(REQUEST_WEEKDAY);
    }

    public void showRequestHoliday() {
        show(REQUEST_HOLIDAY);
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
