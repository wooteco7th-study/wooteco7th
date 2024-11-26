package vendingmachine.dto.request;

import java.util.List;

public record InventoriesRequest(
        List<InventoryRequest> InventoriesRequest
) {
}
