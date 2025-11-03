package lotto.constants;

/**
 * 전역에서 사용하는 입/출력 상수를 정의한 클래스
 */
public final class ViewConstants {
    // 인스턴스화 방지
    private ViewConstants() {
    }

    // 입력 프롬프트
    public static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    public static final String WINNING_NUMBERS_PROMPT = "당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_PROMPT = "보너스 번호를 입력해 주세요.";

    // 통계 출력
    public static final String STATISTICS_HEADER = "당첨 통계";
    public static final String STATISTICS_DIVIDER = "---";

    // 출력 포맷
    public static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.";
    public static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.%n";
}
