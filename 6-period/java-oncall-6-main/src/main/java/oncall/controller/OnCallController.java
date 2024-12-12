package oncall.controller;

import java.util.Stack;
import oncall.domain.OnCall;
import oncall.domain.Schedule;
import oncall.domain.ScheduleGroup;
import oncall.domain.WorkDay;
import oncall.dto.WorkerReceiptGroup;
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
        final ScheduleGroup scheduleGroup = requestScheduleGroup();
        final OnCall onCall = new OnCall(new Stack<>(), scheduleGroup, workDay);
        onCall.assign();
        outputView.printWorkReceipts(WorkerReceiptGroup.of(onCall.getWorkers()).workerReceipts());
    }

    private WorkDay requestWorkDay() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskWorkDay();
            return inputView.readWorkDay();
        });
    }

    private ScheduleGroup requestScheduleGroup() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskWeekdaySchedule();
            final Schedule weekdaySchedule = inputView.readSchedule();
            outputView.printAskWeekendSchedule();
            final Schedule weekendSchedule = inputView.readSchedule();
            return new ScheduleGroup(weekdaySchedule, weekendSchedule);
        });
    }

}
