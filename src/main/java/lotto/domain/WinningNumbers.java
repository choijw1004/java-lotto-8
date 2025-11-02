package lotto.domain;

import java.util.ArrayList;
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
}