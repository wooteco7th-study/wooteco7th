package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.pair.Crew;
import pairmatching.domain.pair.Crews;
import pairmatching.domain.pair.PairHistory;
import pairmatching.util.PairFileReader;

public class Initializer {

    private final Crews backendCrews;
    private final Crews frontendCrews;
    private final PairHistory history;

    public Initializer() {
        this.backendCrews = makeBackendCrews();
        this.frontendCrews = makeFrontendCrews();
        this.history = new PairHistory(new ArrayList<>());
    }

    private Crews makeBackendCrews() {
        List<String> backendNames = readBackendNames();
        List<Crew> backendCrews = backendNames.stream()
                .map(name -> new Crew(Course.백엔드, name))
                .toList();
        return new Crews(backendCrews);
    }

    private Crews makeFrontendCrews() {
        List<String> frontendNames = readFrontendNames();
        List<Crew> frontendCrews = frontendNames.stream()
                .map(name -> new Crew(Course.프론트엔드, name)).toList();
        return new Crews(frontendCrews);
    }


    private List<String> readFrontendNames() {
        return PairFileReader.readFrontends();
    }

    private List<String> readBackendNames() {
        return PairFileReader.readBackends();
    }

    public Crews getBackendCrews() {
        return backendCrews;
    }

    public Crews getFrontendCrews() {
        return frontendCrews;
    }

    public PairHistory getHistory() {
        return history;
    }

    public void clearHistory() {
        history.clear();
    }
}
