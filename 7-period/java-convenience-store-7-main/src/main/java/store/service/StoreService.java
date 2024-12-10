package store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import store.domain.stock.Inventory;
import store.domain.stock.Stocks;
import store.dto.InventoryDto;

public class StoreService {

    public List<InventoryDto> convertToInventoryDtos(final Inventory inventory) {
        return inventory.getInventory().entrySet()
                .stream()
                .map(this::makeInventoryDto)
                .flatMap(List::stream)
                .toList();
    }

    private List<InventoryDto> makeInventoryDto(final Entry<String, Stocks> entry) {
        // - [ ]  만약 재고가 0개라면 `재고 없음`을 출력한다.
        //- [ ]  파일에 프로모션 상품만 있더라도, 일반 상품은 `"재고 없음"`으로 출력한다.
        //    - **일반 상품은 무조건 출력해야한다.**
        Stocks stocks = entry.getValue();
        List<InventoryDto> dtos = new ArrayList<>();
        if (stocks.hasPromotion()) { // 프로모션 상품이 존재할 경우
            dtos.add(makeDto(entry, stocks.hasPromotion()));
        }
        dtos.add(makeDto(entry, false));
        return dtos;
    }

    private InventoryDto makeDto(final Entry<String, Stocks> entry, final boolean hasPromotion) {
        Stocks stocks = entry.getValue();
        return new InventoryDto(entry.getKey(), stocks.getPrice(),
                stocks.getQuantity(stocks, hasPromotion),
                stocks.getPromotionName(hasPromotion));
    }
}
