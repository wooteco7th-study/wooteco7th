package vendingmachine.exception;

import java.util.function.Supplier;
import vendingmachine.view.OutputView;

public class ExceptionHandler {

    private static final int MAX_RETRY = 3;

    private final OutputView outputView;

    public ExceptionHandler(final OutputView outputView) {
        this.outputView = outputView;
    }

    // 최대 시도 횟수만큼 재시도하며 결과를 반환
    public <T> T retryOn(Supplier<T> action) {
        for (int attempt = 0; attempt < MAX_RETRY; attempt++) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                handleError(e, attempt);
            }
        }
        throw new IllegalArgumentException();
    }

    private void handleError(Exception e, int attempt) {
        if (attempt == MAX_RETRY - 1) {
            throw new IllegalArgumentException();
        }
        outputView.showExceptionMessage((e.getMessage()));
    }

    // 실패시 false를 반환하고 계속 진행
    public boolean tryWithoutThrow(Supplier<Boolean> action) {
        try {
            return action.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.showExceptionMessage(e.getMessage());
            return false;
        }
    }

    // 예외 발생시 메시지 출력 후 예외를 다시 던짐
    public <T> T tryWithThrow(Supplier<T> action) {
        try {
            return action.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.showExceptionMessage(e.getMessage());
            throw e;
        }
    }

    // 반환값이 없는 작업 실행, 예외 발생시 메시지 출력 후 예외를 다시 던짐
    public void tryVoid(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.showExceptionMessage(e.getMessage());
            throw e;
        }
    }
}
