package lotto.controller;

import lotto.domain.LottoNumberValidator;
import lotto.domain.LottoParser;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.dto.PurchasedLottos;
import lotto.dto.Statistics;
import lotto.service.LottoMatchingService;
import lotto.service.LottoService;
import lotto.service.StatisticsService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

import static lotto.constants.ErrorMessage.INVALID_LOTTO_NUMBER_FORMAT;
import static lotto.constants.ErrorMessage.INVALID_PURCHASE_AMOUNT_FORMAT;

public class LottoController {

    private final LottoService lottoService;
    private final LottoMatchingService lottoMatchingService;
    private final StatisticsService statisticsService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoService lottoService,
                           LottoMatchingService lottoMatchingService,
                           StatisticsService statisticsService,
                           InputView inputView,
                           OutputView outputView
    ) {
        this.lottoService = lottoService;
        this.lottoMatchingService = lottoMatchingService;
        this.statisticsService = statisticsService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PurchasedLottos purchasedLottos = purchaseLottos();
        WinningNumbers winningNumbers = inputWinningNumbers();

        Map<Rank, Integer> rankCounts = lottoMatchingService.match(purchasedLottos, winningNumbers);
        Statistics statistics = statisticsService.calculate(rankCounts, purchasedLottos.calculateTotalAmount());

        outputView.printStatistics(statistics.rankResults(), statistics.profitRate());
    }


    /**
     * 구입 금액을 입력받아 로또를 구매
     * 예외 발생 시 에러 메시지를 출력하고 재입력
     *
     * @return 구매한 로또 목록
     */
    private PurchasedLottos purchaseLottos() {
        while (true) {
            try {
                int amount = inputView.readPurchaseAmount();
                LottoNumberValidator.validatePriceAmount(amount);
                PurchasedLottos purchasedLottos = lottoService.purchaseLottos(amount);
                outputView.printLottos(purchasedLottos.numbers());

                return purchasedLottos;
            } catch (IllegalArgumentException e) {
                outputView.printError(INVALID_PURCHASE_AMOUNT_FORMAT);
            }
        }
    }

    /**
     * 당첨 번호와 보너스 번호를 입력받음
     * 예외 발생 시 에러 메시지를 출력하고 재입력
     *
     * @return 당첨 번호 객체
     */
    private WinningNumbers inputWinningNumbers() {
        while (true) {
            try {
                String numbersInput = inputView.readWinningNumbers();
                List<Integer> numbers = LottoParser.parseNumbers(numbersInput);
                LottoNumberValidator.validate(numbers);

                String numberInput = inputView.readBonusNumber();
                int bonusNumber = LottoParser.parseBonusNumber(numberInput);
                LottoNumberValidator.validate(bonusNumber);

                return lottoService.createWinningNumbers(numbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError(INVALID_LOTTO_NUMBER_FORMAT);
            }
        }
    }
}
