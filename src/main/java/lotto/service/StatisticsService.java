package lotto.service;

import lotto.domain.Rank;
import lotto.dto.MatchResult;
import lotto.dto.Statistics;

import java.util.Map;

public class StatisticsService {
    private static int ZERO = 0;

    public Statistics calculate(MatchResult matchResult, int purchaseAmount) {
        int totalPrize = calculateTotalPrize(matchResult.rankCounts());
        double profitRate = calculateProfitRate(totalPrize, purchaseAmount);

        return Statistics.of(matchResult, profitRate);
    }

    private int calculateTotalPrize(Map<Rank, Integer> rankCounts) {
        int total = ZERO;

        for (Map.Entry<Rank, Integer> entry : rankCounts.entrySet()) {
            total += entry.getKey().getPrize() * entry.getValue();
        }

        return total;
    }

    private double calculateProfitRate(int totalPrize, int purchaseAmount) {
        return ((double) totalPrize / purchaseAmount) * 100;
    }
}
