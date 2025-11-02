package lotto.view;

import lotto.domain.Lotto;
import lotto.dto.PurchasedLottos;

import java.util.List;

public class OutputView {

    public void printPurchasedLottos(PurchasedLottos purchasedLottos) {
        List<Lotto> lottos = purchasedLottos.lottos();

        System.out.println();
        System.out.printf("%d개를 구매했습니다.%n", lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}
