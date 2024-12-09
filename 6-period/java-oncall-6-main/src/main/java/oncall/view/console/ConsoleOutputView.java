package oncall.view.console;

import java.util.List;
import java.util.stream.Collectors;
import oncall.dto.WorkerDto;
import oncall.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_WORK_DAY = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String ASK_WEEKDAY_WORKERS = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String ASK_WEEKEND_WORKERS = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String WORKER_WITHOUT_HOLIDAY = "%s월 %s일 %s %s";
    private static final String WORKER_WITH_HOLIDAY = "%s월 %s일 %s(휴일) %s";

    @Override
    public void printAskWorkDay() {
        printMessage(ASK_WORK_DAY);
    }

    @Override
    public void printAskWeekdayWorkers() {
        printMessage(ASK_WEEKDAY_WORKERS);
    }

    @Override
    public void printAskWeekendWorkers() {
        printMessage(ASK_WEEKEND_WORKERS);
    }

    @Override
    public void printWorkers(final List<WorkerDto> workerDtos) {
        final String message = workerDtos.stream()
                .map(this::getWorkerMessage)
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + message);
    }

    private String getWorkerMessage(final WorkerDto workerDto) {
        String format = WORKER_WITHOUT_HOLIDAY;
        if (workerDto.isHoliday()) {
            format = WORKER_WITH_HOLIDAY;
        }
        return String.format(format, workerDto.month(), workerDto.dayOfMonth(), workerDto.dayOfWeek(),
                workerDto.name());
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
