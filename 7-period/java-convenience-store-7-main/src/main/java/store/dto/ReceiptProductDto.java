package store.dto;

import java.util.List;

public record ReceiptProductDto(List<TotalProductDto> total, List<GiftProductDto> gifts) {
    // 상품명		수량	금액
    public record TotalProductDto(String name, int quantity, int price) {

    }

    public record GiftProductDto(String name, int quantity) {

    }
}
