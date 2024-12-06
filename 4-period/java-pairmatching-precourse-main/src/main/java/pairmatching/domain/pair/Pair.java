package pairmatching.domain.pair;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pair {

    private final List<String> crews;

    public Pair(final List<String> crews) {
        this.crews = crews;
    }


    public void add(final String crew) {
        crews.add(crew);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair pair)) {
            return false;
        }
        return Objects.equals(crews, pair.crews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crews);
    }

    public List<String> getCrews() {
        return Collections.unmodifiableList(crews);
    }
}
