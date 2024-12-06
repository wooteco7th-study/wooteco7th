package pairmatching.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class ShuffleGenerator {

    public static List<String> shuffleNames(List<String> crewNames) {
        return Randoms.shuffle(crewNames);
    }
}
