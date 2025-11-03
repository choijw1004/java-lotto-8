package lotto.view;

import java.util.List;

import static lotto.constants.ViewConstants.*;

public class OutputView {

    public void printLottos(List<List<Integer>> lottos) {
        System.out.println();
        System.out.println(String.format(PURCHASE_COUNT_FORMAT, lottos.size()));

        for (List<Integer> lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printStatistics(List<String> rankResults, double profitRate) {
        System.out.println();
        System.out.println(STATISTICS_HEADER);
        System.out.println(STATISTICS_DIVIDER);

        for (String result : rankResults) {
            System.out.println(result);
        }

        printProfitRate(profitRate);
    }

    private void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_RATE_FORMAT, profitRate));
    }

    public void printError(String message) {
        System.out.println(message);
    }

}
