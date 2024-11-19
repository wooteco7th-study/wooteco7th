package baseball.view;

import baseball.constant.GameCommand;
import java.util.List;

public interface InputView {

    List<Integer> readNumbers();

    GameCommand readGameCommand();
}
