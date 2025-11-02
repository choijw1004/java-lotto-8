package lotto.dto;

import lotto.domain.Lotto;

import java.util.List;

public record PurchasedLottos(List<Lotto> lottos) {

    public static PurchasedLottos from(List<Lotto> lottos) {
        return new PurchasedLottos(lottos);
    }
}
