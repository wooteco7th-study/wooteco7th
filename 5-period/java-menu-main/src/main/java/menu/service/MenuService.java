package menu.service;

import java.util.List;
import java.util.Map;
import menu.domain.Coach;
import menu.domain.vo.Menu;
import menu.dto.RecommendDto;
import menu.repository.CoachRepository;

public class MenuService {
    private final CoachRepository coachRepository;
    private final MenuRecommendGenerator menuRecommendGenerator;

    public MenuService(final CoachRepository coachRepository, final MenuRecommendGenerator menuRecommendGenerator) {
        this.coachRepository = coachRepository;
        this.menuRecommendGenerator = menuRecommendGenerator;
    }

    public List<Coach> createCoach(final List<Coach> coaches) {
        return coachRepository.saveAll(coaches);
    }

    public void createCoachNoMenu(final Coach coach, final List<Menu> menus) {
        Coach findCoach = coachRepository.findByName(coach.getName());
        findCoach.addNoMenus(menus);
    }

    public RecommendDto getRecommendMenus(final List<Coach> coaches) {
        Map<String, List<String>> recommendResult = menuRecommendGenerator.generate(coaches);
        return new RecommendDto(recommendResult);
    }

}
