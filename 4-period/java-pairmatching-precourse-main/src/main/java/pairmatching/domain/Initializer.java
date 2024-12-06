package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.pair.Crew;
import pairmatching.domain.pair.PairHistory;
import pairmatching.util.PairFileReader;

public class Initializer {

    private final List<Crew> crews;
    private final PairHistory history;

    public Initializer() {
        this.crews = makeCrews();
        this.history = new PairHistory(new ArrayList<>());
    }

    public List<Crew> makeCrews() {
        List<String> frontendNames = readFrontendNames();
        List<String> backendNames = readBackendNames();
        List<Crew> crews = new ArrayList<>();
        List<Crew> frontendCrews = frontendNames.stream()
                .map(name -> new Crew(Course.프론트엔드, name)).toList();
        List<Crew> backendCrews = backendNames.stream()
                .map(name -> new Crew(Course.백엔드, name))
                .toList();
        crews.addAll(frontendCrews);
        crews.addAll(backendCrews);
        return crews;
    }

    private List<String> readFrontendNames() {
        return PairFileReader.readFrontends();
    }

    private List<String> readBackendNames() {
        return PairFileReader.readBackends();
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public PairHistory getHistory() {
        return history;
    }
}
