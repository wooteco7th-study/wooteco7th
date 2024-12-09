package oncall.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Stack;
import oncall.domain.OnCall;
import oncall.domain.WeekdayWorkerGroup;
import oncall.domain.WeekendWorkerGroup;
import oncall.domain.WorkDay;
import oncall.domain.WeekWorkerGroup;
import oncall.dto.WorkerDto;
import oncall.util.LoopTemplate;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {

    private final InputView inputView;
    private final OutputView outputView;

    public OnCallController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final WorkDay workDay = requestWorkDay();
        final WeekWorkerGroup weekWorkerGroup = requestWorkerGroup();
        Console.close();
        final OnCall onCall = new OnCall(workDay, weekWorkerGroup, new Stack<>());
        onCall.assign();
        responseWorkers(onCall);

    }

    private void responseWorkers(final OnCall onCall) {
        final List<WorkerDto> workerDtos = onCall.getWorkers().stream()
                .map(WorkerDto::of)
                .toList();
        outputView.printWorkers(workerDtos);
    }

    private WorkDay requestWorkDay() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskWorkDay();
            return inputView.readWorkDay();
        });
    }

    private WeekWorkerGroup requestWorkerGroup() {
        return LoopTemplate.tryCatchLoop(() -> {
            final WeekdayWorkerGroup weekdayWorkerGroup = requestWeekdayWorkerGroup();
            final WeekendWorkerGroup weekendWorkerGroup = requestWeekendWorkerGroup();
            return new WeekWorkerGroup(weekdayWorkerGroup, weekendWorkerGroup);
        });
    }

    private WeekdayWorkerGroup requestWeekdayWorkerGroup() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskWeekdayWorkers();
            final List<String> workers = inputView.readWorkers();
            return new WeekdayWorkerGroup(workers);
        });
    }

    private WeekendWorkerGroup requestWeekendWorkerGroup() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskWeekendWorkers();
            final List<String> workers = inputView.readWorkers();
            return new WeekendWorkerGroup(workers);
        });
    }

}
