package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.PurchaseAmount;
import lotto.dto.WinningStatisticsInfo;

public class WinningStatistics {
    private final UserLottos userLottos;
    private final WinningNumbers winningNumbers;
    private final Map<Winning, Integer> winningInfoRepository;

    public WinningStatistics(final UserLottos userLottos, final WinningNumbers winningNumbers) {
        this.userLottos = userLottos;
        this.winningNumbers = winningNumbers;
        this.winningInfoRepository = initializeWinningMap();
    }

    public static void main(String[] args) {
        Lotto lotto1 = new Lotto(List.of(1, 2, 38, 30, 13, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 31, 9, 10));
        UserLottos userLottos1 = new UserLottos(List.of(lotto1, lotto2), new PurchaseAmount(8000));
        WinningNumbers winningNumbers1 = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 11)), new BonusNumber(12));
        WinningStatistics winningStatistics = new WinningStatistics(userLottos1, winningNumbers1);
        System.out.println(winningStatistics.compile().getWinningInfo().getProfitPercent());
    }

    private Map<Winning, Integer> initializeWinningMap() {
        Map<Winning, Integer> winningMap = new EnumMap<>(Winning.class);
        Arrays.stream(Winning.values())
                .forEach(winning -> winningMap.put(winning, 0));
        return winningMap;
    }

    public WinningStatistics compile() {
        List<Lotto> userLottos = this.userLottos.getLottos();
        List<Integer> winningNums = winningNumbers.getLottoValue();

        for (Lotto userLotto : userLottos) {
            List<Integer> userNums = userLotto.getLottoNumbers();
            int matchCount = countMatches(userNums, winningNums);
            boolean matchBonus = matchBonus(userNums, winningNumbers.getBonusNumberValue());

            Winning winning = Winning.compile(matchCount, matchBonus);
            winningInfoRepository.merge(winning, 1, Integer::sum);

        }
        return this;
    }

    public WinningStatisticsInfo getWinningInfo() {
        return new WinningStatisticsInfo(winningInfoRepository, calculateProfitPercent());

    }

    private double calculateProfitPercent() {
        double totalProfitAmount = winningInfoRepository.entrySet().stream()
                .mapToInt(winning -> winning.getKey().getPrice() * winning.getValue().intValue())
                .sum();
        int purchaseAmount = userLottos.getPurchaseAmount();

        return (totalProfitAmount / purchaseAmount) * 100;
    }

    private boolean matchBonus(final List<Integer> userNums, final int bonusNumberValue) {
        return userNums.contains(bonusNumberValue);
    }

    private int countMatches(List<Integer> userNumbers, List<Integer> winningNumbers) {
        return (int) userNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public UserLottos getUserLottos() {
        return userLottos;
    }

    public WinningNumbers getWinningNumbers() {
        return winningNumbers;
    }

}
