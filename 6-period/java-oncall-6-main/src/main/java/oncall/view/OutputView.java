package oncall.view;

import java.util.List;
import oncall.dto.WorkerDto;

public interface OutputView {

    void printAskWorkDay();

    void printAskWeekdayWorkers();

    void printAskWeekendWorkers();

    void printWorkers(final List<WorkerDto> workerDtos);
}
