package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.dto.PurchasedLottos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static lotto.constants.ErrorMessage.*;
import static lotto.constants.LottoConstants.*;


public class LottoService {

    /**
     * 구입 금액에 해당하는 개수만큼 로또를 발행
     *
     * @param amount 구입 금액
     * @return 발행된 로또 목록
     */
    public PurchasedLottos purchaseLottos(int amount) {
        int count = calculateLottoCount(amount);

        List<List<Integer>> lottosNumbers = IntStream.range(0, count)
                .mapToObj(i -> generateLotto().getNumbers())
                .toList();

        return PurchasedLottos.from(lottosNumbers);
    }

    /**
     * 입력받은 문자열로 당첨 번호 객체를 생성
     *
     * @param numbers 당첨 번호 리스트
     * @param bonusNumber   보너스 번호
     * @return 당첨 번호 객체
     */
    public WinningNumbers createWinningNumbers(List<Integer> numbers, int bonusNumber) {
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
}
