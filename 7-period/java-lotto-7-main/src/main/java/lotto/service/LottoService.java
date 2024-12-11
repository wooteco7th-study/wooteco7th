package lotto.service;

import lotto.domain.lotto.Lottos;
import lotto.dto.LottoDto;

public class LottoService {
    public LottoDto convertToLottoDto(final Lottos lottos) {
        return LottoDto.of(lottos.getLottos());
    }
}
