package baseball;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.lang.Integer.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    public static List<Integer> requestUserNumber() {
        return Arrays.stream(readLine().trim().split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static YESorNO requestRePlayChoice(){
        return YESorNO.of(parseInt(readLine().trim()));
    }


    public enum YESorNO{
        GAME_RESTART,
        GAME_STOP;

        public static YESorNO of(final int value) {
            if(value == 1) return GAME_RESTART;
            return GAME_STOP;
        }

    }
}
