package menu.service;

import java.util.List;
import menu.domain.Coach;
import menu.domain.vo.Menu;
import menu.dto.RecommendDto;
import menu.repository.CoachRepository;

public class MenuService {
    private final CoachRepository coachRepository;

    public MenuService(final CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> createCoach(final List<Coach> coaches) {
        return coachRepository.saveAll(coaches);
    }

    public List<Coach> createCoachNoMenu(final Coach coach, final List<Menu> menus) {
        return null;
    }

    public RecommendDto getRecommendMenus(final List<Coach> coaches) {
        return null;
    }
}
