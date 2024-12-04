package subway.view;

import static subway.util.RetryOnExceptionTemplate.retryOnException;

import java.util.Scanner;
import java.util.function.Function;
import subway.domain.option.MainOption;
import subway.domain.option.PathCriteriaOption;
import subway.domain.vo.ArrivalStation;
import subway.domain.vo.DepartureStation;
import subway.view.mapper.ModelMapper;

public class RequestView {
    private final String NEW_LINE = System.lineSeparator();
    private final Scanner sc;

    public RequestView(Scanner sc) {
        this.sc = sc;
    }

    public MainOption requestMainOptionSelection() {
        StringBuilder br = new StringBuilder();
        String message1 = "## 원하는 기능을 선택하세요.";
        String message2 = "1. 경로 조회";
        String message3 = "Q. 종료";

        br.append(message1).append(NEW_LINE)
                .append(message2).append(NEW_LINE)
                .append(message3).append(NEW_LINE);

        return requestWithRetry(br.toString(), ModelMapper::toMainOption);
    }

    public PathCriteriaOption requestPathCriteriaSelection() {
        StringBuilder br = new StringBuilder();
        String message1 = "## 원하는 기능을 선택하세요.";
        String message2 = "1. 최단 거리";
        String message3 = "2. 최소 시간";
        String message4 = "B. 돌아 가기";

        br.append(message1).append(NEW_LINE)
                .append(message2).append(NEW_LINE)
                .append(message3).append(NEW_LINE)
                .append(message4).append(NEW_LINE);

        return requestWithRetry(br.toString(), ModelMapper::toPathCriteriaOption);
    }

    public DepartureStation requestDepartureStation() {
        String message = "## 출발역을 입력하세요.";
        return requestWithRetry(message, ModelMapper::toDepartureStation);
    }

    public ArrivalStation requestArrivalStation() {
        String message = "## 도착역을 입력하세요.";
        return requestWithRetry(message, ModelMapper::toArrivalStation);
    }

    private <T> T requestWithRetry(String message, Function<String, T> mapper) {
        return retryOnException(() -> {
            System.out.println(message);
            String input = sc.nextLine().trim();
            System.out.println();
            return mapper.apply(input);
        });
    }

}
