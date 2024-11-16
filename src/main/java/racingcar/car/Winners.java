package racingcar.car;

import java.util.List;

public record Winners(
        List<String> winners
){
    public String formatWinners() {
        return String.join(", ", winners);
    }

}
