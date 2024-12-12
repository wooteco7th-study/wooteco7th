package menu.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public enum Menu {

    규동("규동", Category.일식), 우동("우동", Category.일식), 미소시루("미소시루", Category.일식), 스시("스시", Category.일식), 가츠동("가츠동",
            Category.일식), 오니기리(
            "오니기리", Category.일식), 하이라이스("하이라이스", Category.일식), 라멘("라멘", Category.일식), 오코노미야끼("오코노미야끼", Category.일식),

    김밥("김밥", Category.한식), 김치찌개("김치찌개", Category.한식), 쌈밥("쌈밥", Category.한식), 된장찌개("된장찌개", Category.한식), 비빔밥("비빔밥",
            Category.한식), 칼국수("칼국수",
            Category.한식), 불고기("불고기", Category.한식), 떡볶이("떡볶이", Category.한식), 제육볶음("제육볶음", Category.한식),

    깐풍기("깐풍기", Category.중식), 볶음면("볶음면", Category.중식), 동파육("동파육", Category.중식), 짜장면("짜장면", Category.중식), 짬뽕("짬뽕",
            Category.중식), 마파두부("마파두부", Category.중식), 탕수육("탕수육", Category.중식), 토마토("토마토 달걀볶음", Category.중식), 고추잡채("고추잡채",
            Category.중식),

    팟타이("팟타이", Category.아시안), 카오("카오 팟", Category.아시안), 나시고렝("나시고렝", Category.아시안), 파인애플("파인애플 볶음밥", Category.아시안), 쌀국수(
            "쌀국수", Category.아시안), 똠얌꿍("똠얌꿍", Category.아시안), 반미("반미", Category.아시안), 월남쌈("월남쌈", Category.아시안), 분짜("분짜",
            Category.아시안),
    라자냐("라자냐", Category.양식), 그라탱("그라탱", Category.양식), 뇨끼("뇨끼", Category.양식), 끼슈("끼슈", Category.양식), 프렌치("프렌치 토스트",
            Category.양식), 바게트("바게트", Category.양식), 스파게티("스파게티", Category.양식), 피자("피자", Category.양식), 파니니("파니니",
            Category.양식),

    없음("", Category.NONE);


    private static final List<Menu> VALUED_MENUS = Arrays.stream(Menu.values())
            .filter(menu -> menu != 없음)
            .toList();

    private final String menuName;
    private final Category category;

    Menu(final String menuName, final Category category) {
        this.menuName = menuName;
        this.category = category;
    }

    public static Menu fromWithoutNone(String input) {
        return VALUED_MENUS.stream()
                .filter(menuName -> Objects.equals(menuName.menuName, input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME));
    }

    public static Menu from(String input) {
        return VALUED_MENUS.stream()
                .filter(menuName -> Objects.equals(menuName.menuName, input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME));
    }

    public static List<String> findMenuNames(final Category category) {
        return VALUED_MENUS.stream()
                .filter(menu -> menu.category.equals(category))
                .map(Menu::getMenuName)
                .toList();
    }

    public static List<Menu> filteredMenus(List<Menu> menuToExclude) {
        return VALUED_MENUS.stream()
                .filter(menu -> !menuToExclude.contains(menu))
                .toList();
    }

    public String getMenuName() {
        return menuName;
    }

    public Category getCategory() {
        return category;
    }
}
