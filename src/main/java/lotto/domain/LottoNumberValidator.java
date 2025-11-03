package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.constants.ErrorMessage.*;

public class LottoNumberValidator {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private LottoNumberValidator() {
    }

    public static void validate(int number) {
        validateNumber(number);
    }

    public static void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    public static void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE);
        }
    }

    public static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_SIZE);
        }
    }

    public static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_DUPLICATE);
        }
    }

    public static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateNumber(number);
        }
    }

    public static void validateBonusNotInNumbers(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_DUPLICATE);
        }
    }
}
