package oncall.dto;

// 5월 1일 월 준팍
public record TurnDto(int monthNumber, int dayNumber, String day, String name, boolean isSpecialDay) {

}
