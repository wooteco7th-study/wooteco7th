package pairmatching.view;

import pairmatching.domain.Mission;
import pairmatching.domain.PairCommand;
import pairmatching.domain.RematchCommand;

public interface InputView {

    PairCommand readPairCommand();

    RematchCommand readRematchCommand();

    Mission readMission();

}
