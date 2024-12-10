package pairmatching.view;

import java.util.List;
import pairmatching.dto.FairMatchingResponseDto;
import pairmatching.dto.MatchDto;
import pairmatching.view.io.ConsoleOutputView;

public class ResponseView {
    private final String NEW_LINE = System.lineSeparator();


    private final ConsoleOutputView consoleOut;

    public ResponseView(final ConsoleOutputView consoleOut) {
        this.consoleOut = consoleOut;
    }

    public void printFairMatchingBoard() {
        String message = new StringBuilder()
                .append("#############################################").append(NEW_LINE)
                .append("미션:").append(NEW_LINE)
                .append("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임").append(NEW_LINE)
                .append("  - 레벨2: 장바구니 | 결제 | 지하철노선도").append(NEW_LINE)
                .append("  - 레벨3:").append(NEW_LINE)
                .append("  - 레벨4: 성능개선 | 배포").append(NEW_LINE)
                .append("  - 레벨5:").append(NEW_LINE)
                .append("############################################")
                .toString();
        consoleOut.println(message);
    }

    public void printFairMatchingResult(FairMatchingResponseDto dto) {
        StringBuilder br = new StringBuilder();
        br.append("페어 매칭 결과입니다.").append(NEW_LINE);
        List<MatchDto> matchResult = dto.getMatchResult();
        for (int i = 0; i < matchResult.size(); i++) {
            List<String> matchNames = matchResult.get(i).getMatchNames();
            for (int j = 0; j < matchNames.size() - 1; j++) {
                br.append(matchNames.get(j)).append(" : ");
            }
            br.append(matchNames.get(matchNames.size() - 1)).append(NEW_LINE);
        }

        String message = br.toString();
        consoleOut.println(message);
    }

    /*
    - ### 초기화 메시지 출력하기

     */
    public void printFormatMessage() {
        String message = new StringBuilder()
                .append("초기화 되었습니다.").toString();
        consoleOut.println(message);
    }

    public void printError(final String message) {
        consoleOut.println(message);
    }
}
