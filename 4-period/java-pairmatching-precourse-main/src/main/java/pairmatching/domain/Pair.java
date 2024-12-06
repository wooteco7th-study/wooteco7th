package pairmatching.domain;

import java.util.Collections;
import java.util.List;

public class Pair {

    private final List<String> crews;

    public Pair(final List<String> crews) {
        this.crews = crews;
    }

    public boolean hasCrews(final List<String> crews) {
        return crews.stream()
                .anyMatch(this.crews::contains);
    }

    public List<String> getCrews() {
        return Collections.unmodifiableList(crews);
    }
}
