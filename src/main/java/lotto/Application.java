package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.controller.LottoController;
import lotto.service.LottoMatchingService;
import lotto.service.LottoService;
import lotto.service.StatisticsService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        try{
            LottoController lottoController = new LottoController(
                    new LottoService(),
                    new LottoMatchingService(),
                    new StatisticsService(),
                    new InputView(),
                    new OutputView()
            );
            lottoController.run();
        } finally {
            Console.close();
        }
    }
}
