package vendingmachine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import vendingmachine.dto.CoinDto;
import vendingmachine.domain.price.HoldingPrice;
import vendingmachine.domain.price.Price;
import vendingmachine.domain.price.coin.Coin;
import vendingmachine.domain.price.coin.CoinGenerator;

public class VendingService {

    private final CoinGenerator coinGenerator;

    public VendingService(final CoinGenerator coinGenerator) {
        this.coinGenerator = coinGenerator;
    }

    public HoldingPrice createHoldingPrice(long amount) {
        return new HoldingPrice(amount);
    }

    public List<CoinDto> createRandomCoins(final Price holdingPrice) {
        Map<Coin, Integer> coins = coinGenerator.generateCoins(holdingPrice);
        List<CoinDto> coinDtos = new ArrayList<>();
        for (Entry<Coin, Integer> entry : coins.entrySet()) {
            coinDtos.add(new CoinDto(entry.getKey().getPrice().getAmount(), entry.getValue()));
        }
        return coinDtos;
    }
}
