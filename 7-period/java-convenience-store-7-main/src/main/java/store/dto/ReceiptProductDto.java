package store.dto;

import java.util.List;

public record ReceiptProductDto(List<TotalProductDto> total, List<GiftProductDto> gifts) {
    // 상품명		수량	금액

    public static ReceiptProductDto of(List<ResultDto> dtos) {
        List<TotalProductDto> totalProductDtos = dtos.stream()
                .map(dto -> new TotalProductDto(dto.productName(), getTotalQuantity(dto), dto.price()))
                .toList();
        List<GiftProductDto> giftProductDtos = dtos.stream()
                .filter(dto -> dto.giftQuantity() > 0)
                .map(dto -> new GiftProductDto(dto.productName(), dto.giftQuantity()))
                .toList();
        return new ReceiptProductDto(totalProductDtos, giftProductDtos);
    }

    private static int getTotalQuantity(ResultDto dto) {
        return dto.promotionPurchaseQuantity() + dto.regularPurchaseQuantity();
    }

    public record TotalProductDto(String name, int quantity, int price) {

    }

    public record GiftProductDto(String name, int quantity) {

    }
}
