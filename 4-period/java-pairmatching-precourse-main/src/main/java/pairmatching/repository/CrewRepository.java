package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Crew;

public class CrewRepository {
    private final List<Crew> crews = new ArrayList<>();
    private final FileLoader fileLoader = new FileLoader();

    public CrewRepository() {
        List<Crew> backend = fileLoader.initializeBackend();
        List<Crew> frontend = fileLoader.initializeFrontend();
        backend.forEach(crews::add);
        frontend.forEach(crews::add);
    }

}
