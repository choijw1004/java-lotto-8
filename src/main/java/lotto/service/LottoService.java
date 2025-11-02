package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.dto.PurchasedLottos;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;

    public PurchasedLottos purchaseLottos(int amount) {
        validateAmount(amount);
        int count = calculateLottoCount(amount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }

        return PurchasedLottos.from(lottos);
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 이상이어야 합니다."
            );
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위로 입력해야 합니다."
            );
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / LOTTO_PRICE;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
