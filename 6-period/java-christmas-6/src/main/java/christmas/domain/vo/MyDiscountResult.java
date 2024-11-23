package christmas.domain.vo;

import christmas.domain.DiscountResult;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDiscountResult {
    // 할인 이벤트 : 금액
   private final List<DiscountResult> discountResults;

    public MyDiscountResult(final List<DiscountResult> discountResults) {
        this.discountResults = discountResults;
    }

    public List<DiscountResult> getDiscountResults() {
        return discountResults;
    }
}
