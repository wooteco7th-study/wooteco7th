package menu.view.mapper;

import static menu.constant.ExceptionMessage.INVALID_INPUT_FORM;

import java.util.List;
import java.util.stream.Collectors;
import menu.domain.Coach;
import menu.domain.validator.CoachValidator;
import menu.domain.validator.NoMenuValidator;
import menu.domain.vo.Menu;

public class ModelMapper {
    private ModelMapper() {
    }

    /*
        코치별 못 먹는 음식들 입력 받기
        - 먹지 못하는 메뉴가 없으면 빈 값을 입력한다.
    */
    public static List<Menu> toNoMenus(String input) {

        List<String> menuNames = RequestParser.parseNames(input.trim());
        NoMenuValidator.validateDuplicateNoMenus(menuNames);
        List<Menu> noMenus = menuNames.stream().map(cantName -> Menu.toMenu(cantName))
                .collect(Collectors.toList());

        return noMenus;
    }

    // 코치 이름들
    public static List<Coach> toCoaches(String input) {
        String pattern = RequestPattern.createItemListPattern();
        RequestValidator.validateInput(input, pattern, INVALID_INPUT_FORM.getMessage());

        List<String> names = RequestParser.parseNames(input.trim());
        CoachValidator.validateCoachSize(names);
        List<Coach> coaches = names.stream().map(name -> new Coach(name)).collect(Collectors.toList());

        return coaches;
    }

}
