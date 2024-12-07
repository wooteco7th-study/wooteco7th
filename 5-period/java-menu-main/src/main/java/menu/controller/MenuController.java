package menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /*
                            "[ 구구 | 김치찌개 | 스파게티 | 규동 | 짜장면 | 카오 팟 ]",
                            "[ 제임스 | 제육볶음 | 라자냐 | 가츠동 | 짬뽕 | 파인애플 볶음밥 ]",
     */
    public void run() {
        requestView.printFirstMessage();
        var coaches = createCoach();
        addNoMenus(coaches);
//        getRecommendMenus(coaches);
        Map<String, List<String>> q = new HashMap<>();
        q.put("구구", List.of("김치찌개", "스파게티", "규동", "짜장면", "카오 팟"));
        q.put("제임스", List.of("제육볶음", "라자냐", "가츠동", "짬뽕", "파인애플 볶음밥"));

        responseView.responseRecommendMenu(new RecommendDto(q));
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
        return coaches;
    }

}
