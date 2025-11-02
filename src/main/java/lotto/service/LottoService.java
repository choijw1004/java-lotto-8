package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.dto.PurchasedLottos;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_PRICE = 1000;
    private static final String DELIMITER = ",";

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
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_SIZE);
        return new Lotto(numbers);
    }

    private List<Integer> parseNumbers(String input) {
        String[] tokens = input.split(DELIMITER);
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            try {
                int number = Integer.parseInt(token.trim());
                numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자로 입력해야 합니다.");
            }
        }

        return numbers;
    }

    private int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자로 입력해야 합니다.");
        }
    }
}
