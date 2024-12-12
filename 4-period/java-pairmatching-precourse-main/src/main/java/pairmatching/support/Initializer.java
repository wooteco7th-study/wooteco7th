package pairmatching.support;

import java.util.List;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.order.Course;
import pairmatching.util.PairFileReader;

public class Initializer {

    public Crews makeBackendCrews() {
        List<String> backendNames = readBackendNames();
        List<Crew> backendCrews = backendNames.stream()
                .map(name -> new Crew(Course.백엔드, name))
                .toList();
        return new Crews(backendCrews);
    }

    public Crews makeFrontendCrews() {
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
}
