package lotto.controller;

import lotto.domain.WinningNumbers;
import lotto.dto.MatchResult;
import lotto.dto.PurchasedLottos;
import lotto.dto.Statistics;
import lotto.service.LottoMatchingService;
import lotto.service.LottoService;
import lotto.service.StatisticsService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final LottoService lottoService;
    private final LottoMatchingService lottoMatchingService;
    private final StatisticsService statisticsService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoService lottoService, LottoMatchingService lottoMatchingService, StatisticsService statisticsService, InputView inputView, OutputView outputView) {
        this.lottoService = lottoService;
        this.lottoMatchingService = lottoMatchingService;
        this.statisticsService = statisticsService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchasedLottos purchasedLottos = purchaseLottos();
        WinningNumbers winningNumbers = inputWinningNumbers();

        MatchResult matchResult = lottoMatchingService.match(purchasedLottos, winningNumbers);
        Statistics statistics = statisticsService.calculate(matchResult, getPurchaseAmount(purchasedLottos));

        outputView.printStatistics(statistics.matchResult().rankCounts(), statistics.profitRate());
    }

    private PurchasedLottos purchaseLottos() {
        while (true) {
            try {
                int amount = inputView.readPurchaseAmount();
                PurchasedLottos purchasedLottos = lottoService.purchaseLottos(amount);
                outputView.printLottos(purchasedLottos.lottos());
                return purchasedLottos;
            } catch (IllegalArgumentException e) {
                outputView.printError("[ERROR] 구입 금액은 숫자로 입력해야 합니다.");
            }
        }
    }


    private WinningNumbers inputWinningNumbers() {
        while (true) {
            try {
                String numbersInput = inputView.readWinningNumbers();
                String bonusInput = inputView.readBonusNumberInput();
                List<Integer> numbers = parseNumbers(numbersInput);
                int bonusNumber = parseBonusNumber(bonusInput);

                return new WinningNumbers(numbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError("[ERROR] 로또 번호는 숫자로 입력해야 합니다.");
            }
        }
    }

    private int getPurchaseAmount(PurchasedLottos purchasedLottos) {
        return purchasedLottos.lottos().size() * 1000;
    }

    private List<Integer> parseNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            numbers.add(Integer.parseInt(token.trim()));
        }

        return numbers;
    }

    private int parseBonusNumber(String input) {
        return Integer.parseInt(input.trim());
    }
}
