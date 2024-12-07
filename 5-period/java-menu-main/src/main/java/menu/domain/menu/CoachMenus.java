package menu.domain.menu;

import java.util.List;
import menu.domain.name.Name;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

// 코치 + 못먹는 메뉴
// 코치 + 이번주 먹을 메뉴
public class CoachMenus {

    private final Name coachName;
    private final List<Menu> menus;

    public CoachMenus(final Name coachName, final List<String> menus) {
        validate(menus);
        this.coachName = coachName;
        this.menus = makeMenus(menus);
    }

    private List<Menu> makeMenus(final List<String> inputs) {
        return inputs.stream()
                .map(Menu::from)
                .toList();
    }

    private void validate(final List<String> menus) {
        if (menus.size() != menus.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME_DUPLICATED);
        }
        if (menus.size() > 2) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MENU_NAME_NUMBER);
        }
    }

    // - 코치가 이번주에 먹을 메뉴 이름들
    //- 5가지의 메뉴
    //    - 코치마다 한 주에 **중복되지 않는 메뉴를 추천**한다.
    // 못먹는 메뉴

}
