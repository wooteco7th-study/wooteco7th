package christmas.domain.vo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyDiscountEvent {
    // 할인 이벤트 : 금액
   private final Map<String,Integer> discountEvents;

    private MyDiscountEvent(final Map<String, Integer> discountEvents) {
        this.discountEvents = discountEvents;
    }
    public MyDiscountEvent initialize(){
        return new MyDiscountEvent(new HashMap<>());
    }
    public Map addDiscount(final String discountName, final int amount){
         discountEvents.merge(discountName, amount, Integer::sum);
         return Collections.unmodifiableMap(this.discountEvents);
    }

}
