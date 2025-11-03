package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.dto.PurchasedLottos;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoMatchingService {

    /**
     * 구매한 로또들과 당첨 번호를 비교하여 등수별 당첨 개수를 계산
     *
     * @param purchasedLottos 구매한 로또 목록
     * @param winningNumbers  당첨 번호
     * @return 등수별 당첨 개수 (Map<Rank, Integer>)
     */
    public Map<Rank, Integer> match(PurchasedLottos purchasedLottos, WinningNumbers winningNumbers) {
        Map<Rank, Integer> rankCounts = initializeRankCounts();

        for (List<Integer> lottoNumbers : purchasedLottos.numbers()) {
            Lotto lotto = new Lotto(lottoNumbers);
            Rank rank = matchOne(lotto, winningNumbers);
            rankCounts.put(rank, rankCounts.get(rank) + 1);
        }

        return rankCounts;
    }

    /**
     * 등수별 카운트를 0으로 초기화
     * 순서 보장하는 LinkedHashMap
     */
    private Map<Rank, Integer> initializeRankCounts() {
        Map<Rank, Integer> rankCounts = new LinkedHashMap<>();

        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }

        return rankCounts;
    }

    private Rank matchOne(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = countMatches(lotto, winningNumbers);
        boolean hasBonus = checkBonus(lotto, winningNumbers);
        return Rank.of(matchCount, hasBonus);
    }

    private int countMatches(Lotto lotto, WinningNumbers winningNumbers) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        List<Integer> winningNumbersList = winningNumbers.getNumbers();

        return (int) lottoNumbers.stream()
                .filter(winningNumbersList::contains)
                .count();
    }

    private boolean checkBonus(Lotto lotto, WinningNumbers winningNumbers) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        int bonusNumber = winningNumbers.getBonusNumber();

        return lottoNumbers.contains(bonusNumber);
    }
}
