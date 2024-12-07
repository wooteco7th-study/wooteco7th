package menu.domain.vo;

import static menu.constant.ExceptionMessage.INVALID_MENU_NAME_RANGE;
import static menu.domain.vo.Category.ASIA;
import static menu.domain.vo.Category.CH;
import static menu.domain.vo.Category.JP;
import static menu.domain.vo.Category.KR;
import static menu.domain.vo.Category.WEST;

import java.util.Arrays;

/*
일식: 규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼
한식: 김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음
중식: 깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채
아시안: 팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜
양식: 라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니
 */
public enum Menu {
    // 일본
    규동("규동", JP), 우동("우동", JP), 미소시루("미소시루", JP), 스시("스시", JP),
    가츠동("가츠동", JP), 오니기리("오니기리", JP), 하이라이스("하이라이스", JP), 라멘("라멘", JP), 오코노미야끼("오코노미야끼", JP),

    // 한국
    김밥("김밥", KR), 김치찌개("김치찌개", KR), 쌈밥("쌀밥", KR), 된장찌개("된장찌개", KR), 비빔밥("비빔밥", KR), 칼국수("칼국수", KR),
    불고기("불고기", KR), 떡볶이("떡볶이", KR), 제육볶음("제육볶음", KR),

    // 중국
    깐풍기("깐풍기", CH), 볶음면("볶음면", CH), 동파육("동파육", CH), 짜장면("짜장면", CH), 짬뽕("짬뽕", CH),
    마파두부("마파두부", CH), 탕수육("탕수육", CH), 토마토("토마토", CH), 달걀볶음("달걀볶음", CH), 고추잡채("고추잡채", CH),

    // 아시아
    팟타이("팟타이", ASIA), 카오_팟("카오 팟", ASIA), 나시고렝("나시고랭", ASIA), 파인애플_볶음밥("파인애플 볶음밥", ASIA),
    쌀국수("쌀국수", ASIA), 똠얌꿍("똠얌꿍", ASIA), 반미("반미", ASIA), 월남쌈("월남쌈", ASIA), 분짜("분짜", ASIA),

    // 서양
    라자냐("라자냐", WEST), 그라탱("그라탱", WEST), 뇨끼("뇨끼", WEST), 끼슈("끼슈", WEST), 프렌치_토스트("프렌치 토스트", WEST),
    바게트("바게트", WEST), 스파게티("스파게티", WEST), 피자("피자", WEST), 파니니("파니니", WEST),

    NONE("", Category.NONE);


    private final String value;
    private final Category category;

    Menu(final String value, final Category category) {
        this.value = value;
        this.category = category;
    }

    public static Menu toMenu(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.getValue().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MENU_NAME_RANGE.getMessage()));
    }

    public String getValue() {
        return value;
    }

    public Category getCategory() {
        return category;
    }
}
