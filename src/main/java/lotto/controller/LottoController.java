package lotto.controller;

import lotto.dto.PurchasedLottos;
import lotto.service.LottoMatchingService;
import lotto.service.LottoService;
import lotto.service.StatisticsService;
import lotto.view.InputView;

public class LottoController {

    private final LottoService lottoService;
    private final LottoMatchingService lottoMatchingService;
    private final StatisticsService statisticsService;
    private final InputView inputView;

    public LottoController(LottoService lottoService, LottoMatchingService lottoMatchingService, StatisticsService statisticsService, InputView inputView) {
        this.lottoService = lottoService;
        this.lottoMatchingService = lottoMatchingService;
        this.statisticsService = statisticsService;
        this.inputView = inputView;
    }
}
