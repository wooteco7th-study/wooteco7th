package pairmatching.view;

import static pairmatching.util.RetryOnExceptionTemplate.retryOnException;

import java.util.function.Function;
import pairmatching.domain.vo.FunctionOption;
import pairmatching.domain.vo.RematchOption;
import pairmatching.dto.FairMatchingRequestDto;
import pairmatching.view.io.ConsoleInputView;
import pairmatching.view.io.ConsoleOutputView;
import pairmatching.view.mapper.ModelMapper;

public class RequestView {
    private final String NEW_LINE = System.lineSeparator();
    private final String START_HEADER = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final ConsoleInputView consoleIn;
    private final ConsoleOutputView consoleOut;

    public RequestView(final ConsoleInputView consoleIn, final ConsoleOutputView consoleOut) {
        this.consoleIn = consoleIn;
        this.consoleOut = consoleOut;
    }

    public FunctionOption requestFunctionOption() {
        String message = new StringBuilder()
                .append("기능을 선택하세요.").append(NEW_LINE)
                .append("1. 페어 매칭").append(NEW_LINE)
                .append("2. 페어 조회").append(NEW_LINE)
                .append("3. 페어 초기화").append(NEW_LINE)
                .append("Q. 종료").append(NEW_LINE)
                .toString();
        return request(message, ModelMapper::toFunctionOption);
    }

    public FairMatchingRequestDto requestFairMatching() {
        String message = new StringBuilder()
                .append("과정, 레벨, 미션을 선택하세요.").append(NEW_LINE)
                .append("ex) 백엔드, 레벨1, 자동차경주").append(NEW_LINE)
                .toString();
        return request(message, ModelMapper::toFaitMatchingDto);
    }

    public RematchOption aksPairRematch() {
        String message = new StringBuilder()
                .append("매칭 정보가 있습니다. 다시 매칭하시겠습니까?").append(NEW_LINE)
                .append("네 | 아니오").append(NEW_LINE).toString();

        return request(message, ModelMapper::toRematchOption);
    }


    private <T> T request(String message, Function<String, T> mapper) {
        return retryOnException(() -> {
            consoleOut.println(message);
            String input = consoleIn.readLine();
            return mapper.apply(input);
        });
    }

}
