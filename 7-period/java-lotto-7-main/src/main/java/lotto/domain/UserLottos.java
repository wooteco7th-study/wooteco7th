package lotto.domain;

import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.PurchaseAmount;

public class UserLottos {
    private final List<Lotto> lottos;
    private final PurchaseAmount purchaseAmount;

    public UserLottos(final List<Lotto> lottos, final PurchaseAmount purchaseAmount) {
        this.lottos = lottos;
        this.purchaseAmount = purchaseAmount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getPurchaseAmount() {
        return purchaseAmount.getValue();
    }
}
