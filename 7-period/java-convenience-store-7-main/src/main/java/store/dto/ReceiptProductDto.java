package store.dto;

import java.util.List;

public record ReceiptProductDto(List<TotalProductDto> total, List<GiftProductDto> gifts) {
    // 상품명		수량	금액

    public static ReceiptProductDto of(List<ResultDto> dtos) {
        List<TotalProductDto> totalProductDtos = makeTotalProductDtos(dtos);
        List<GiftProductDto> giftProductDtos = makeGiftProductDtos(dtos);
        return new ReceiptProductDto(totalProductDtos, giftProductDtos);
    }

    private static List<GiftProductDto> makeGiftProductDtos(final List<ResultDto> dtos) {
        return dtos.stream()
                .filter(dto -> dto.giftQuantity() > 0)
                .map(dto -> new GiftProductDto(dto.productName(), dto.giftQuantity()))
                .toList();
    }

    private static List<TotalProductDto> makeTotalProductDtos(final List<ResultDto> dtos) {
        return dtos.stream()
                .map(dto -> new TotalProductDto(dto.productName(), getTotalQuantity(dto),
                        getTotalPrice(dto.price(), getTotalQuantity(dto))))
                .toList();
    }

    private static int getTotalQuantity(ResultDto dto) {
        return dto.promotionPurchaseQuantity() + dto.regularPurchaseQuantity();
    }

    public static int getTotalPrice(int price, int quantity) {
        return price * quantity;
    }

    public record TotalProductDto(String name, int quantity, int price) {

    }

    public record GiftProductDto(String name, int quantity) {

    }
}
