package pairmatching.view;

import java.util.List;
import pairmatching.dto.PairName;

public interface OutputView {

    void printPairCommand();

    void printAskMission();

    void printMatchResult(final List<PairName> pairNames);

    void printAskRematch();

    void printClear();
}
