package menu.repository;

import java.util.HashMap;
import java.util.Map;
import menu.domain.Coach;

public class CoachRepository {
    private final Map<String, Coach> repository = new HashMap<>();

    public CoachRepository() {
    }

    public void save(Coach coach) {
        repository.put(coach.getName(), coach);
    }

    public Coach findByName(String name) {
        return repository.get(name);
    }
}
