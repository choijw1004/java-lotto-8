package lotto.service;

import lotto.domain.Rank;
import lotto.dto.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsService {

    /**
     * 당첨 통계와 수익률을 계산
     *
     * @param rankCounts     등수별 당첨 개수
     * @param purchaseAmount 구입 금액
     * @return 통계 결과 (출력용 문자열 리스트 + 수익률)
     */
    public Statistics calculate(Map<Rank, Integer> rankCounts, int purchaseAmount) {
        int totalPrize = calculateTotalPrize(rankCounts);
        double profitRate = calculateProfitRate(totalPrize, purchaseAmount);

        List<String> rankResults = formatRankResults(rankCounts);

        return Statistics.of(rankResults, profitRate);
    }

    private int calculateTotalPrize(Map<Rank, Integer> rankCounts) {
        int total = 0;

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
