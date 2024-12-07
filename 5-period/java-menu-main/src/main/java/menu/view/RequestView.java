package menu.view;

import static menu.util.RetryOnExceptionTemplate.retryOnException;

import java.util.List;
import java.util.function.Function;
import menu.domain.Coach;
import menu.domain.vo.Menu;
import menu.view.io.ConsoleInputView;
import menu.view.io.ConsoleOutputView;
import menu.view.mapper.ModelMapper;

public class RequestView {
    private final String NEW_LINE = System.lineSeparator();
    private final ConsoleInputView consoleIn;
    private final ConsoleOutputView consoleOut;

    public RequestView(final ConsoleInputView consoleIn, final ConsoleOutputView consoleOut) {
        this.consoleIn = consoleIn;
        this.consoleOut = consoleOut;
    }

    public void printFirstMessage() {
        StringBuilder br = new StringBuilder();
        br.append("점심 메뉴 추천을 시작합니다.").append(NEW_LINE);
        System.out.println(br.toString());
    }

    public List<Coach> requestCoachNames() {
        StringBuilder br = new StringBuilder();
        br.append("코치의 이름을 입력해 주세요. (, 로 구분)");
        String message = br.toString();
        return request(message, ModelMapper::toCoaches);

    }

    public List<Menu> requestNo_Menus(String coachName) {
        String message = new StringBuilder()
                .append(coachName).append("(이)가 못 먹는 메뉴를 입력해 주세요.").append(NEW_LINE)
                .toString();
        return request(message, ModelMapper::toNoMenus);
    }


    private <T> T request(String message, Function<String, T> mapper) {
        return retryOnException(() -> {
            consoleOut.println(message);
            String input = consoleIn.readLine();
            return mapper.apply(input);
        });
    }

}
