package pairmatching.dto;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.pair.Pair;

public record PairMatchResultDto(List<List<String>> results) {
    public static PairMatchResultDto from(final List<Pair> pairs) {
        List<List<String>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            result.add(pair.getCrews());
        }
        return new PairMatchResultDto(result);
    }
}
