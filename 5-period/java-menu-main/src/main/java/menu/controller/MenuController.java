package menu.controller;

import java.util.List;
import menu.domain.Coach;
import menu.domain.vo.Menu;
import menu.dto.RecommendDto;
import menu.service.MenuService;
import menu.view.RequestView;
import menu.view.ResponseView;

public class MenuController {
    private final RequestView requestView;
    private final ResponseView responseView;
    private final MenuService menuService;


    public MenuController(final RequestView requestView, final ResponseView responseView,
                          final MenuService menuService) {
        this.requestView = requestView;
        this.responseView = responseView;
        this.menuService = menuService;
    }

    public void run() {
        requestView.printFirstMessage();
        var coaches = createCoach();
        addNoMenus(coaches);
        getRecommendMenus(coaches);

    }

    private void getRecommendMenus(final List<Coach> coaches) {
        RecommendDto recommendDto = menuService.getRecommendMenus(coaches);
        responseView.responseRecommendMenu(recommendDto);
    }

    private void addNoMenus(final List<Coach> coaches) {
        for (Coach coach : coaches) {
            List<Menu> menus = requestView.requestNo_Menus(coach.getName());
            menuService.createCoachNoMenu(coach, menus);
        }
    }

    private List<Coach> createCoach() {
        List<Coach> coaches = requestView.requestCoachNames();
        List<Coach> createdCoaches = menuService.createCoach(coaches);
        return createdCoaches;
    }

}
