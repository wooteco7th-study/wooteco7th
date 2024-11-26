package vendingmachine.dto.request;

public record InventoryRequest(
        String name,
        int price,
        int stock
) {
}
