package lotto.service;

import lotto.domain.Rank;
import lotto.dto.Statistics;

import java.util.*;

import static lotto.constants.ViewConstants.PRIZE_FORMAT;
import static lotto.constants.ViewConstants.RANK_RESULT_FORMAT;

public class StatisticsService {

    /**
     * 당첨 통계와 수익률을 계산
     *
     * @param rankCounts     등수별 당첨 개수
     * @param purchaseAmount 구입 금액
     * @return 통계 결과 (출력용 문자열 리스트 + 수익률)
     */
    public Statistics calculate(Map<Rank, Integer> rankCounts, int purchaseAmount) {
        long totalPrize = calculateTotalPrize(rankCounts);
        double profitRate = calculateProfitRate(totalPrize, purchaseAmount);

        List<String> rankResults = formatRankResults(rankCounts);

        return Statistics.of(rankResults, profitRate);
    }

    private long calculateTotalPrize(Map<Rank, Integer> rankCounts) {
        long total = 0;

        for (Map.Entry<Rank, Integer> entry : rankCounts.entrySet()) {
            total += (long) entry.getKey().getPrize() * entry.getValue();
        }

        return total;
    }

    private double calculateProfitRate(long totalPrize, int purchaseAmount) {
        return ((double) totalPrize / purchaseAmount) * 100;
    }

    /**
     * 등수별 결과를 출력용 문자열 리스트로 변환
     * 5등(낮은 상금)부터 1등(높은 상금) 순서로 정렬하여 반환
     *
     * @param rankCounts 등수별 당첨 개수
     * @return 출력용 문자열 리스트
     */
    private List<String> formatRankResults(Map<Rank, Integer> rankCounts) {
        return Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .sorted(Comparator.comparingInt(Rank::getPrize))
                .map(rank -> {
                    int count = rankCounts.getOrDefault(rank, 0);
                    String prize = formatPrize(rank.getPrize());
                    return String.format(RANK_RESULT_FORMAT,
                            rank.getMessage(),
                            prize,
                            count);
                })
                .toList();
    }

    private String formatPrize(int prize) {
        return String.format(PRIZE_FORMAT, prize);
    }
}
