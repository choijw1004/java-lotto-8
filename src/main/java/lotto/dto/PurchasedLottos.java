package lotto.dto;

import java.util.List;

public record PurchasedLottos(List<List<Integer>> numbers){

    public static PurchasedLottos from(List<List<Integer>> numbers) {
        return new PurchasedLottos(numbers);
    }
}
