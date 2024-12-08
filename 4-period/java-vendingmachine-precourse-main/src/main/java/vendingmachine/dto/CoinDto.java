package vendingmachine.dto;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.price.coin.Coin;

public record CoinDto(long coinType, long quantity) {

    public static List<CoinDto> from(Map<Coin, Integer> coins) {
        return coins.entrySet().stream()
                .map(entry -> new CoinDto(entry.getKey().getPrice().getAmount(), entry.getValue()))
                .toList();
    }
}
