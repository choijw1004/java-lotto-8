package lotto.dto;

public record Statistics(MatchResult matchResult, double profitRate) {

    public static Statistics of(MatchResult matchResult, double profitRate) {
        return new Statistics(matchResult, profitRate);
    }
}
