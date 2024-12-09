package pairmatching.view.console;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.domain.CourseType;
import pairmatching.domain.LevelType;
import pairmatching.domain.Mission;
import pairmatching.domain.MissionType;
import pairmatching.domain.PairCommand;
import pairmatching.domain.RematchCommand;
import pairmatching.error.ErrorMessage;
import pairmatching.util.ListValidator;
import pairmatching.util.StringParser;
import pairmatching.view.InputView;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_COMMA = ",";
    private static final String SPACE = " ";
    private static final int COURSE_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int MISSION_INDEX = 2;

    @Override
    public PairCommand readPairCommand() {
        return PairCommand.findByCommand(readInput());
    }

    @Override
    public RematchCommand readRematchCommand() {
        return RematchCommand.findByCommand(readInput());
    }

    @Override
    public Mission readMission() {
        final List<String> tokens = StringParser.parseToTokens(StringParser.removePattern(readInput(), SPACE),
                DELIMITER_COMMA);
        ListValidator.validateSize(tokens, 3, ErrorMessage.INVALID_INPUT_FORMAT);
        final LevelType level = LevelType.findByName(tokens.get(LEVEL_INDEX));
        final CourseType course = CourseType.findByName(tokens.get(COURSE_INDEX));
        final MissionType mission = MissionType.findByLevelAndName(level, tokens.get(MISSION_INDEX));
        return new Mission(level, course, mission);
    }

    private String readInput() {
        return Console.readLine();
    }
}
