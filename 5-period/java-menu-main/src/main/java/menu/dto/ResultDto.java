package menu.dto;

import java.util.List;

// [ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]
//[ 카테고리 | 한식 | 한식 | 일식 | 중식 | 아시안 ]
//[ 토미 | 쌈밥 | 김치찌개 | 미소시루 | 짜장면 | 팟타이 ]
//[ 제임스 | 된장찌개 | 비빔밥 | 가츠동 | 토마토 달걀볶음 | 파인애플 볶음밥 ]
//[ 포코 | 된장찌개 | 불고기 | 하이라이스 | 탕수육 | 나시고렝 ]
public record ResultDto(List<List<String>> menus) {

}
