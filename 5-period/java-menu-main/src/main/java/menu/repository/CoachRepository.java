package menu.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;

public class CoachRepository {
    private final Map<String, Coach> repository = new HashMap<>();

    public CoachRepository() {
    }

    public List<Coach> saveAll(List<Coach> coaches) {
        for (Coach coach : coaches) {
            repository.put(coach.getName(), coach);
        }
        return coaches;
    }

    public void save(Coach coach) {
        repository.put(coach.getName(), coach);
    }

    public Coach findByName(String name) {
        return repository.get(name);
    }
}
