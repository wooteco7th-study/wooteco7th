package pairmatching.domain.pair;

import java.util.Collections;
import java.util.List;

public class Crews {

    private final List<Crew> crews;

    public Crews(final List<Crew> crews) {
        this.crews = crews;
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }
}
