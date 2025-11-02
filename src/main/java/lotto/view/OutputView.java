package lotto.view;

import lotto.domain.Lotto;
import lotto.dto.PurchasedLottos;

import java.util.List;

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

}
