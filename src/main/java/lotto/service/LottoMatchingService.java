package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.dto.MatchResult;
import lotto.dto.PurchasedLottos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoMatchingService {
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public MatchResult match(PurchasedLottos purchasedLottos, WinningNumbers winningNumbers) {
        Map<Rank, Integer> rankCounts = initializeRankCounts();

        for (Lotto lotto : purchasedLottos.lottos()) {
            Rank rank = matchOne(lotto, winningNumbers);
            rankCounts.put(rank, rankCounts.get(rank) + ONE);
        }

        return MatchResult.from(rankCounts);
    }

    private Map<Rank, Integer> initializeRankCounts() {
        Map<Rank, Integer> rankCounts = new HashMap<>();

        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, ZERO);
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

        int count = 0;

        for (int lottoNumber : lottoNumbers) {
            if (winningNumbersList.contains(lottoNumber)) {
                count++;
            }
        }
        return count;
    }

    private boolean checkBonus(Lotto lotto, WinningNumbers winningNumbers) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        int bonusNumber = winningNumbers.getBonusNumber();

        return lottoNumbers.contains(bonusNumber);
    }
}
