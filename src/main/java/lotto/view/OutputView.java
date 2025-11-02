package lotto.view;

import java.util.List;

public class OutputView {

    public void printLottos(List<List<Integer>> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");

        for (List<Integer> lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printStatistics(List<String> rankResults, double profitRate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        for (String result : rankResults) {
            System.out.println(result);
        }

        printProfitRate(profitRate);
    }

    private void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    public void printError(String message) {
        System.out.println(message);
    }

}
