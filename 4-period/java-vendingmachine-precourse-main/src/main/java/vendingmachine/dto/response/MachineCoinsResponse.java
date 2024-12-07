package vendingmachine.dto.response;

public record MachineCoinsResponse(
        int quantityOf500,
        int quantityOf100,
        int quantityOf50,
        int quantityOf10
) {
}
