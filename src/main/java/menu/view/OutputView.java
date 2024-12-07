package menu.view;

import menu.domain.CoachName;
import menu.domain.Coaches;
import menu.domain.menu.Category;
import menu.domain.menu.Menu;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_MSG = NEW_LINE + "메뉴 추천 결과입니다.";
    private static final String DAY_FORMAT_MSG = "[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]";
    private static final String CATEGORY_FORMAT = "[ 카테고리 | %s ]";
    private static final String FINISH_MSG = NEW_LINE + "추천을 완료했습니다.";
    private static final String MENU_FORMAT = "[ %s | %s ]";
    private static final String DELIMITER = " | ";


    public void printResult(final List<Category> categories, final Map<CoachName, List<Menu>> recommendationResult, final Coaches coaches) {
        System.out.println(RESULT_MSG);
        System.out.println(DAY_FORMAT_MSG);
        printCategory(categories);
        printMenu(recommendationResult, coaches);
        System.out.println(FINISH_MSG);
    }

    private void printCategory(final List<Category> categories) {
        List<String> names = categories.stream()
                .map(Enum::name)
                .collect(Collectors.toList());

        System.out.printf(CATEGORY_FORMAT + NEW_LINE, String.join(DELIMITER, names));
    }

    private void printMenu(final Map<CoachName, List<Menu>> recommendationResult, final Coaches coaches) {
        for (CoachName coach : coaches.getCoachNames()) {
            List<Menu> menus = recommendationResult.get(coach);
            List<String> menu = menus.stream()
                    .map(Menu::getName)
                    .collect(Collectors.toList());

            System.out.printf(MENU_FORMAT + NEW_LINE, coach.getName(), String.join(DELIMITER, menu));
        }
    }

    public static void printError(String error) {
        System.out.println(error);
    }
}
