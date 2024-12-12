package oncall.dto;

import java.util.List;
import java.util.Stack;
import oncall.domain.Worker;

public record WorkerReceiptGroup(
        List<WorkerReceipt> workerReceipts
) {

    public static WorkerReceiptGroup of(final Stack<Worker> workers) {
        return new WorkerReceiptGroup(
                workers.stream()
                        .map(WorkerReceipt::of)
                        .toList()
        );
    }

    public record WorkerReceipt(
            int month,
            int dayOfMonth,
            String dayOfWeek,
            String name
    ) {
        public static WorkerReceipt of(final Worker worker) {
            return new WorkerReceipt(
                    worker.getMonth(),
                    worker.getDayOfMonth(),
                    worker.getDayOfWeek(),
                    worker.getName()
            );
        }
    }
}
