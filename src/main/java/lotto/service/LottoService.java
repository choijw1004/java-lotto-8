package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.dto.PurchasedLottos;

import java.util.ArrayList;
import java.util.List;

import static lotto.constants.ErrorMessage.*;
import static lotto.constants.LottoConstants.*;


public class LottoService {

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
                    INVALID_PURCHASE_AMOUNT_MIN_PREFIX + LOTTO_PRICE + INVALID_PURCHASE_AMOUNT_MIN_SUFFIX
            );
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    INVALID_PURCHASE_AMOUNT_UNIT_PREFIX + LOTTO_PRICE + INVALID_PURCHASE_AMOUNT_UNIT_SUFFIX
            );
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / LOTTO_PRICE;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_SIZE);
        return new Lotto(numbers);
    }

    private List<Integer> parseNumbers(String input) {
        String[] tokens = input.split(DELEMITER);
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            try {
                numbers.add(Integer.parseInt(token.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INVALID_WINNING_NUMBER_FORMAT);
            }
        }

        return numbers;
    }

    private int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_FORMAT);
        }
    }
}
