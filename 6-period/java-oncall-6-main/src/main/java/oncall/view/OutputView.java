package oncall.view;

import java.util.List;
import java.util.stream.Collectors;
import oncall.dto.WorkerReceiptGroup.WorkerReceipt;

public class OutputView {

    /**
     * 비상 근무를 배정할 월과 시작 요일을 입력하세요> 5,월 평일 비상 근무 순번대로 사원 닉네임을 입력하세요> 준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리 휴일 비상 근무 순번대로 사원
     * 닉네임을 입력하세요> 수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니 5월 1일 월 준팍 5월 5일 금(휴일) 루루
     */

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_WORK_DAY = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String ASK_WEEKDAY_SCHEDULE = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String ASK_WEEKEND_SCHEDULE = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String WORKER_WITH_HOLIDAY = "%d월 %d일 %s(휴일) %s";
    private static final String WORKER_WITHOUT_HOLIDAY = "%d월 %d일 %s %s";

    public void printAskWorkDay() {
        printMessage(ASK_WORK_DAY);
    }

    public void printAskWeekdaySchedule() {
        printMessage(ASK_WEEKDAY_SCHEDULE);
    }

    public void printAskWeekendSchedule() {
        printMessage(ASK_WEEKEND_SCHEDULE);
    }

    public void printWorkReceipts(final List<WorkerReceipt> workerReceipts) {
        final String message = workerReceipts.stream()
                .map(this::createWorkReceiptMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + message);
    }

    private String createWorkReceiptMessage(final WorkerReceipt workerReceipt) {
        String format = WORKER_WITHOUT_HOLIDAY;
        if (workerReceipt.isWeekdayAndHoliday()) {
            format = WORKER_WITH_HOLIDAY;
        }
        return String.format(format, workerReceipt.month(), workerReceipt.dayOfMonth(), workerReceipt.dayOfWeek(),
                workerReceipt.name());
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
