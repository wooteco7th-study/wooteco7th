package oncall.dto;

import oncall.domain.Worker;

public record WorkerDto(
        String month,
        String dayOfMonth,
        String dayOfWeek,
        String name,
        boolean isHoliday
) {

    public static WorkerDto of(final Worker worker) {
        return new WorkerDto(
                String.valueOf(worker.getMonth().getNumber()),
                String.valueOf(worker.getDayOfMonth()),
                worker.getDayOfWeek().getName(),
                worker.getName(),
                worker.isHoliday()
        );
    }
}
