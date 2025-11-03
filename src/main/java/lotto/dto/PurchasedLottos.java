package lotto.dto;

import java.util.List;

import static lotto.constants.LottoConstants.LOTTO_PRICE;

public record PurchasedLottos(List<List<Integer>> numbers) {

    public static PurchasedLottos from(List<List<Integer>> numbers) {
        return new PurchasedLottos(numbers);
    }

    public int calculateTotalAmount() {
        return numbers.size() * LOTTO_PRICE;
    }
}
