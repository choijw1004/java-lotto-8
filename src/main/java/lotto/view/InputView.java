package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.constants.ViewConstants.*;

public class InputView {

    public int readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
        String input = Console.readLine();

        return Integer.parseInt(input);
    }

    public String readWinningNumbers() {
        System.out.println();
        System.out.println(WINNING_NUMBERS_PROMPT);
        return Console.readLine();
    }

    public String readBonusNumber() {
        System.out.println();
        System.out.println(BONUS_NUMBER_PROMPT);
        return Console.readLine();
    }
}
