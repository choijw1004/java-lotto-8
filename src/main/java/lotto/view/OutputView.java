package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.dto.PurchasedLottos;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");

        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }

    public void printStatistics(Map<Rank, Integer> rankCounts, double profitRate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        printRankCounts(rankCounts);
        printProfitRate(profitRate);
    }

    private void printRankCounts(Map<Rank, Integer> rankCounts) {
        for (Rank rank : Rank.values()) {
            if (rank.isWinning()) {
                int count = rankCounts.getOrDefault(rank, 0);
                String prize = formatPrize(rank.getPrize());
                System.out.println(
                        rank.getMessage() + " (" + prize + ") - " + count + "개"
                );
            }
        }
    }

    private void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    private String formatPrize(int prize) {
        return String.format("%,d원", prize);
    }

    public void printError(String message) {
        System.out.println(message);
    }

}
