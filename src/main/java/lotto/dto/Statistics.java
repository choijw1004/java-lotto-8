package lotto.dto;

import java.util.List;

public record Statistics(List<String> rankResults, double profitRate) {

    public static Statistics of(List<String> rankResults, double profitRate) {
        return new Statistics(rankResults, profitRate);
    }
}
