package menu.view;

import java.util.List;
import java.util.Map;
import menu.dto.RecommendDto;
import menu.view.io.ConsoleOutputView;

public class ResponseView {
    private final String NEW_LINE = System.lineSeparator();

    private final ConsoleOutputView consoleOut;

    public ResponseView(final ConsoleOutputView consoleOut) {
        this.consoleOut = consoleOut;
    }


    public void responseRecommendMenu(final RecommendDto dto) {
        StringBuilder br = new StringBuilder();
        br.append("메뉴 추천 결과입니다.").append(NEW_LINE)
                .append("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]").append(NEW_LINE)
                .append("[ 카테고리 | 한식 | 양식 | 일식 | 중식 | 아시안 ]").append(NEW_LINE);
        Map<String, List<String>> recommendResult = dto.getRecommendResult();
        recommendResult.entrySet().forEach(coachRecommend ->
                br.append("[ ")
                        .append(coachRecommend.getKey())
                        .append(" | ")
                        .append(String.join(" | ", coachRecommend.getValue()))
                        .append(" ]")
                        .append(NEW_LINE)
        );
        br.append(NEW_LINE).append("추천을 완료했습니다.");
        consoleOut.println(br.toString());
    }


}
