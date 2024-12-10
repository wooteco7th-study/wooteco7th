package pairmatching.repository;

import static pairmatching.domain.vo.Course.BACKEND;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.vo.Course;

public class CrewRepository {
    private final List<Crew> crews = new ArrayList<>();
    private final FileLoader fileLoader = new FileLoader();

    public CrewRepository() {
        List<Crew> backend = fileLoader.initializeBackend();
        List<Crew> frontend = fileLoader.initializeFrontend();
        backend.forEach(crews::add);
        frontend.forEach(crews::add);
    }

    public List<String> findAllByCourse(final Course course) {
        if (course.equals(BACKEND)) {
            return crews.stream()
                    .filter(Crew::isBackend)
                    .map(crew -> crew.getName()).collect(Collectors.toList());
        }
        return crews.stream()
                .filter(Crew::isFrontend)
                .map(crew -> crew.getName()).collect(Collectors.toList());
    }

}
