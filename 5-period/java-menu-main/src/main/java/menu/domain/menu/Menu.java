package menu.domain.menu;

import java.util.Arrays;
import java.util.Objects;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public enum Menu {

    규동("규동", MenuType.일식), 우동("우동", MenuType.일식), 미소시루("미소시루", MenuType.일식), 스시("스시", MenuType.일식), 가츠동("가츠동",
            MenuType.일식), 오니기리(
            "오니기리", MenuType.일식), 하이라이스("하이라이스", MenuType.일식), 라멘("라멘", MenuType.일식), 오코노미야끼("오코노미야끼", MenuType.일식),

    김밥("김밥", MenuType.한식), 김치찌개("김치찌개", MenuType.한식), 쌈밥("쌈밥", MenuType.한식), 된장찌개("된장찌개", MenuType.한식), 비빔밥("비빔밥",
            MenuType.한식), 칼국수("칼국수",
            MenuType.한식), 불고기("불고기", MenuType.한식), 떡볶이("떡볶이", MenuType.한식), 제육볶음("제육볶음", MenuType.한식),

    깐풍기("깐풍기", MenuType.중식), 볶음면("볶음면", MenuType.중식), 동파육("동파육", MenuType.중식), 짜장면("짜장면", MenuType.중식), 짬뽕("짬뽕",
            MenuType.중식), 마파두부("마파두부", MenuType.중식), 탕수육("탕수육", MenuType.중식), 토마토("토마토 달걀볶음", MenuType.중식), 고추잡채("고추잡채",
            MenuType.중식),

    팟타이("팟타이", MenuType.아시안), 카오("카오 팟", MenuType.아시안), 나시고렝("나시고렝", MenuType.아시안), 파인애플("파인애플 볶음밥", MenuType.아시안), 쌀국수(
            "쌀국수", MenuType.아시안), 똠얌꿍("똠얌꿍", MenuType.아시안), 반미("반미", MenuType.아시안), 월남쌈("월남쌈", MenuType.아시안), 분짜("분짜",
            MenuType.아시안),
    라자냐("라자냐", MenuType.양식), 그라탱("그라탱", MenuType.양식), 뇨끼("뇨끼", MenuType.양식), 끼슈("끼슈", MenuType.양식), 프렌치("프렌치 토스트",
            MenuType.양식), 바게트("바게트", MenuType.양식), 스파게티("스파게티", MenuType.양식), 피자("피자", MenuType.양식), 파니니("파니니",
            MenuType.양식),

    없음("", MenuType.NONE);

    // 일식: 규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼
    //한식: 김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음
    //중식: 깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채
    //아시안: 팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜
    //양식: 라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니

    private final String menuName;
    private final MenuType menuType;

    Menu(final String menuName, final MenuType menuType) {
        this.menuName = menuName;
        this.menuType = menuType;
    }

    public static Menu from(String input) {
        return Arrays.stream(Menu.values())
                .filter(menuName -> Objects.equals(menuName.menuName, input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME));
    }
}
