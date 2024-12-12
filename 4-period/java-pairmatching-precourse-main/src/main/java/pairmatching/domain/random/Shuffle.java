package pairmatching.domain.random;

import java.util.List;

@FunctionalInterface
public interface Shuffle {

    List<String> shuffleCrews(List<String> tokens);
}
