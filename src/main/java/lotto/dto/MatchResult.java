package lotto.dto;

import lotto.domain.Rank;

import java.util.Map;

public record MatchResult(Map<Rank, Integer> rankCounts) {

    public static MatchResult from(Map<Rank, Integer> rankCounts) {
        return new MatchResult(rankCounts);
    }
}
