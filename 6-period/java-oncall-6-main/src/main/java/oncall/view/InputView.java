package oncall.view;

import java.util.List;
import oncall.domain.WorkDay;

public interface InputView {

    WorkDay readWorkDay();

    List<String> readWorkers();
}
