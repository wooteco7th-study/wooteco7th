package lotto.dto;

import java.util.List;
import lotto.domain.UserLottos;
import lotto.domain.vo.Lotto;

public class UserLottosInfo {
    private final UserLottos userLottos;
    private final int lottoQuantity;

    public UserLottosInfo(final UserLottos userLottos, final int lottoQuantity) {
        this.userLottos = userLottos;
        this.lottoQuantity = lottoQuantity;
    }

    public List<Lotto> getUserLottosValue() {
        return userLottos.getLottos();
    }

    public UserLottos getUserLottos() {
        return userLottos;
    }

    public int getLottoQuantity() {
        return lottoQuantity;
    }
}
