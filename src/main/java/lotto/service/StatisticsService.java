package lotto.service;

import lotto.domain.Rank;
import lotto.dto.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsService {
    private static final int ZERO = 0;

    public Statistics calculate(Map<Rank, Integer> rankCounts, int purchaseAmount) {
        int totalPrize = calculateTotalPrize(rankCounts);
        double profitRate = calculateProfitRate(totalPrize, purchaseAmount);

        List<String> rankResults = formatRankResults(rankCounts);

        return Statistics.of(rankResults, profitRate);
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

    private List<String> formatRankResults(Map<Rank, Integer> rankCounts) {
        List<String> results = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            if (rank.isWinning()) {
                int count = rankCounts.getOrDefault(rank, 0);
                String prize = formatPrize(rank.getPrize());
                results.add(rank.getMessage() + " (" + prize + ") - " + count + "개");
            }
        }

        return results;
    }

    private String formatPrize(int prize) {
        return String.format("%,d원", prize);
    }
}
