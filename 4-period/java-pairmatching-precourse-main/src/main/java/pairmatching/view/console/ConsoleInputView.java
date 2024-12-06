package pairmatching.view.console;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.PairCommand;
import pairmatching.domain.RematchCommand;
import pairmatching.view.InputView;

public class ConsoleInputView implements InputView {

    @Override
    public PairCommand readPairCommand() {
        return PairCommand.findByCommand(readInput());
    }

    @Override
    public RematchCommand readRematchCommand() {
        return RematchCommand.findByCommand(readInput());
    }

    private String readInput() {
        return Console.readLine();
    }
}
