package lotto.domain;

import java.util.Arrays;
import java.util.List;

import static lotto.constants.ErrorMessage.INVALID_BONUS_NUMBER_FORMAT;
import static lotto.constants.ErrorMessage.INVALID_WINNING_NUMBER_FORMAT;
import static lotto.constants.LottoConstants.DELEMITER;

public final class LottoParser {
    private LottoParser() {
    }

    public static List<Integer> parseNumbers(String input) {
        List<Integer> numbers = Arrays.stream(input.split(DELEMITER))
                .map(String::trim)
                .map(token -> {
                    try {
                        return Integer.parseInt(token);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(INVALID_WINNING_NUMBER_FORMAT);
                    }
                })
                .toList();
        return numbers;
    }

    public static int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_FORMAT);
        }
    }
}
