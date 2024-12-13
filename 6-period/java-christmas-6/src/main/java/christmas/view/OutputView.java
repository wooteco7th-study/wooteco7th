package christmas.view;

import christmas.dto.DiscountDetailsDto;
import christmas.dto.OrderDto;
import java.util.List;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String REQUEST_VISIT_DAY = """
            안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)""";
    private static final String REQUEST_ORDER = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String INFORM_TITLE_DISCOUNT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String TITLE_ORDER_MENU = "<주문 메뉴>";
    private static final String FORMAT_MENU_QUANTITY = "%s %d개";

    private static final String TITLE_TOTAL_ORDER_PRICE = "<할인 전 총주문 금액>";
    private static final String FORMAT_PRICE = "%,d원";
    private static final String TITLE_GIFT_MENU = "<증정 메뉴>";
    private static final String TITLE_DISOUNT_DETAILS = "<혜택 내역>";
    private static final String TITLE_TOTAL_DISCOUNT = "<총혜택 금액>";
    private static final String FORMAT_DISCOUNT = "-%,d원";

    private static final String TITLE_ESTIMATED_PRICE = "<할인 후 예상 결제 금액>";
    private static final String FORMAT_ESTIMATED_PRICE = "%,d원";
    private static final String TITLE_BADGE = "<12월 이벤트 배지>";

    private static final String NONE = "없음";
    private static final String FORMAT_CHRISTMAS_DDAY = "크리스마스 디데이 할인: -%,d원";
    private static final String FORMAT_WEEKDAY_DISCOUNT = "평일 할인: -%,d원";
    private static final String FORMAT_WEEKEND_DISCOUNT = "주말 할인: -%,d원";
    private static final String FORMAT_SPECIAL_DISCOUNT = "특별 할인: -%,d원";
    private static final String FORMAT_GIFT_DISCOUNT = "증정 이벤트: -%,d원";

    public void showRequestVisitDay() {
        showln(REQUEST_VISIT_DAY);
    }

    public void showRequestOrder() {
        showln(REQUEST_ORDER);
    }

    public void showInformTitleDiscount(int day) {
        showln(format(INFORM_TITLE_DISCOUNT, day));
    }

    public void showTitleOrderMenu(final List<OrderDto> dtos) {
        showln(LINE + TITLE_ORDER_MENU);
        dtos.stream()
                .map(dto -> format(FORMAT_MENU_QUANTITY, dto.name(), dto.quantity()))
                .forEach(this::showln);
    }

    public void showTitleTotalOrderPrice(final int price) {
        showln(LINE + TITLE_TOTAL_ORDER_PRICE);
        showln(format(FORMAT_PRICE, price));
    }

    public void showTitleGiftMenu(final List<OrderDto> dtos) {
        showln(LINE + TITLE_GIFT_MENU);
        if (dtos.isEmpty()) {
            showln(NONE);
            return;
        }
        dtos.stream()
                .map(dto -> format(FORMAT_MENU_QUANTITY, dto.name(), dto.quantity()))
                .forEach(this::showln);
    }

    public void showTitleDiscountDetails(final DiscountDetailsDto dto) {
        showln(LINE + TITLE_DISOUNT_DETAILS);
        if (dto.isEmpty()) {
            showln(NONE);
            return;
        }
        checkChristmasDday(dto);
        checkDayDiscount(dto);
        checkSpecialDiscount(dto);
        checkGiftEvent(dto);
    }

    private void checkChristmasDday(final DiscountDetailsDto dto) {
        if (dto.christmasDday() != 0) {
            showln(format(FORMAT_CHRISTMAS_DDAY, dto.christmasDday()));
        }
    }

    private void checkGiftEvent(final DiscountDetailsDto dto) {
        if (dto.giftEvent() != 0) {
            showln(format(FORMAT_GIFT_DISCOUNT, dto.giftEvent()));
        }
    }

    private void checkSpecialDiscount(final DiscountDetailsDto dto) {
        if (dto.specialDiscount() != 0) {
            showln(format(FORMAT_SPECIAL_DISCOUNT, dto.specialDiscount()));
        }
    }

    private void checkDayDiscount(final DiscountDetailsDto dto) {
        if (dto.dayDiscount() != 0) {
            if (dto.isWeekday()) {
                showln(format(FORMAT_WEEKDAY_DISCOUNT, dto.dayDiscount()));
                return;
            }
            showln(format(FORMAT_WEEKEND_DISCOUNT, dto.dayDiscount()));
        }
    }

    public void showTitleTotalDiscount(int totalDiscount) {
        showln(LINE + TITLE_TOTAL_DISCOUNT);
        if (totalDiscount == 0) {
            showln(format(FORMAT_PRICE, totalDiscount));
            return;
        }
        showln(format(FORMAT_DISCOUNT, totalDiscount));
    }

    public void showTitleEstimatedPrice(int estimatedPrice) {
        showln(LINE + TITLE_ESTIMATED_PRICE);
        showln(format(FORMAT_ESTIMATED_PRICE, estimatedPrice));
    }

    public void showTitleBadge(String name) {
        showln(LINE + TITLE_BADGE);
        showln(name);
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
