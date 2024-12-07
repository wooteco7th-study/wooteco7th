package menu.service;

import menu.repository.CoachRepository;

public class MenuService {
    private final CoachRepository coachRepository;

    public MenuService(final CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
}
