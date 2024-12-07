package menu.exception;

public enum ErrorMessage {

    //- [ ]  0,1명이거나 6명 이상일 경우(범위)
    INVALID_COACH_NUMBER_MIN("코치는 최소 2명 이상 입력해야 합니다."),
    INVALID_COACH_NUMBER_MAX("코치는 최대 5명 이하로 입력해야 합니다."),

    // - [ ]  문자가 아닐 경우
    //- [ ]  0,1글자이거나 5글자 이상일 경우 (범위)
    INVALID_INPUT_FORMAT("유효한 입력 형식이 아닙니다."),
    INVALID_COACH_NAME("유효한 코치 이름이 아닙니다."),
    //- [ ]  중복된 이름일 경우
    INVALID_COACH_NAME_DUPLICATED("코치 이름이 중복 입력되었습니다."),
    INVALID_INPUT("유효한 입력이 아닙니다."),

    // - [ ]  존재하지 않은 메뉴일 경우
    //- [ ]  중복된 메뉴를 입력할 경우
    //- [ ]  메뉴를 3개 이상 입력할 경우
    INVALID_MENU_NAME("존재하지 않은 메뉴입니다."),
    INVALID_MENU_NAME_DUPLICATED("중복된 메뉴입니다."),
    INVALID_MENU_NAME_NUMBER("메뉴를 3개 이상 입력했습니다."),

    // - [ ]  한 주에 같은 **카테고리는 최대 2회**까지만 고를 수 있다.
    //- [ ]  한 주에 **중복되지 않는 메뉴를 추천**한다.
    INVALID_CATEGORY_DUPLICATED("한 주에 같은 카테고리는 최대 2회까지만 고를 수 있습니다."),
    INVALID_MENU_DUPLICATED("한 주에 같은 메뉴를 고를 수 없습니다."),
    INVALID_MENU_COACH_CANNOT_EAT("해당 코치가 먹지 못하는 메뉴입니다."),
    INVALID_MENU_NOT_RECOMMEND("추천할 수 없는 메뉴입니다."),
    ;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
