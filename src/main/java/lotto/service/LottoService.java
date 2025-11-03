package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.dto.PurchasedLottos;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_PRICE = 1000;
    private static final String DELEMITER = ",";

    public PurchasedLottos purchaseLottos(int amount) {
        validateAmount(amount);
        int count = calculateLottoCount(amount);

        List<List<Integer>> lottosNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = generateLotto();
            lottosNumbers.add(lotto.getNumbers());
        }

        return PurchasedLottos.from(lottosNumbers);
    }

    public WinningNumbers createWinningNumbers(String numbersInput, String bonusInput) {
        List<Integer> numbers = parseNumbers(numbersInput);
        int bonusNumber = parseBonusNumber(bonusInput);

        return new WinningNumbers(numbers, bonusNumber);
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
        String[] tokens = input.split(DELEMITER);
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            try {
                numbers.add(Integer.parseInt(token.trim()));
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
