package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinningNumbers {
    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        LottoNumberValidator.validate(numbers);
        LottoNumberValidator.validate(bonusNumber);
        LottoNumberValidator.validateBonusNotInNumbers(numbers, bonusNumber);

        this.numbers = new ArrayList<>(numbers);
        this.bonusNumber = bonusNumber;

    }

    /**
     * 당첨 번호 리스트를 외부에서 수정할 수 없도록 반환하는 메서드
     *
     * @return 당첨 번호의 불변 리스트
     */
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}