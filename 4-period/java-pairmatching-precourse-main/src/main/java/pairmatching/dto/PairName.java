package pairmatching.dto;

import java.util.List;
import pairmatching.domain.Pair;

public record PairName(
        List<String> crews
) {
    public static PairName of(final Pair pair) {
        return new PairName(pair.getCrews());
    }
}
