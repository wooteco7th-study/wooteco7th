package menu.domain.menu;

import java.util.Arrays;
import java.util.List;

import static menu.domain.menu.Category.아시안;
import static menu.domain.menu.Category.양식;
import static menu.domain.menu.Category.없음;
import static menu.domain.menu.Category.일식;
import static menu.domain.menu.Category.중식;
import static menu.domain.menu.Category.한식;
import static menu.exception.ExceptionMessage.MENU_NOT_FOUND;

public enum Menu {
    NONE(없음, "없음"),

    //일식
    규동(일식, "규동"),

    우동(일식, "우동"),

    미소시루(일식, "미소시루"),

    스시(일식, "스시"),

    가츠동(일식, "가츠동"),

    오니기리(일식, "오니기리"),

    하이라이스(일식, "하이라이스"),

    라멘(일식, "라멘"),
    오코노미야끼(일식, "오코노미야끼"),

    //한식
    김밥(한식, "김밥"),
    김치찌개(한식, "김치찌개"),
    쌈밥(한식, "쌈밥"),
    된장찌개(한식, "된장찌개"),
    비빔밥(한식, "비빔밥"),
    칼국수(한식, "칼국수"),
    불고기(한식, "불고기"),
    떡볶이(한식, "떡볶이"),
    제육볶음(한식, "제육볶음"),

    //중식
    깐풍기(중식, "깐풍기"),
    볶음면(중식, "볶음면"),
    동파육(중식, "동파육"),
    짜장면(중식, "짜장면"),
    짬뽕(중식, "짬뽕"),
    마파두부(중식, "마파두부"),
    탕수육(중식, "탕수육"),
    토마토달걀볶음(중식, "토마토 달걀볶음"),
    고추잡채(중식, "고추잡채"),

    //아시안
    팟타이(아시안, "팟타이"),
    카오팟(아시안, "카오 팟"),
    나시고렝(아시안, "나시고렝"),
    파인애플볶음밥(아시안, "파인애플 볶음밥"),
    쌀국수(아시안, "쌀국수"),
    똠얌꿍(아시안, "똠양꿍"),
    반미(아시안, "반미"),
    월남쌈(아시안, "월남쌈"),
    분짜(아시안, "분짜"),

    //양식
    라자냐(양식, "라자냐"),
    그라탱(양식, "그라탱"),
    뇨끼(양식, "뇨끼"),
    끼슈(양식, "끼슈"),
    프렌치토스트(양식, "프렌치 토스트"),
    바게트(양식, "바게트"),
    스파게티(양식, "스파게티"),
    피자(양식, "피자"),
    파니니(양식, "파니니");

    private final Category category;
    private final String name;

    Menu(final Category category, final String name) {
        this.category = category;
        this.name = name;
    }

    public static Menu from(String input) {
        return Arrays.stream(Menu.values())
                .filter(element -> element.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MENU_NOT_FOUND.getMessage()));
    }

    public static List<String> findAllMenuInSameCategory(Category category) {
        return Arrays.stream(Menu.values())
                .filter(element -> element.category == category)
                .map(menu -> menu.name)
                .toList();
    }
}
