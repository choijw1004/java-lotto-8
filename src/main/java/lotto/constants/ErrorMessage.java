package lotto.constants;

/**
 * 전역에서 사용하는 에러 메시지를 정의한 클래스
 */
public final class ErrorMessage {
    //인스턴스화 방지
    private ErrorMessage() {
    }

    // 구입 금액 관련
    public static final String INVALID_PURCHASE_AMOUNT_FORMAT = "[ERROR] 구입 금액은 숫자로 입력해야 합니다.";
    public static final String INVALID_PURCHASE_AMOUNT_MIN_PREFIX = "[ERROR] 구입 금액은 ";
    public static final String INVALID_PURCHASE_AMOUNT_MIN_SUFFIX = "원 이상이어야 합니다.";

    public static final String INVALID_PURCHASE_AMOUNT_UNIT_PREFIX = "[ERROR] 구입 금액은 ";
    public static final String INVALID_PURCHASE_AMOUNT_UNIT_SUFFIX = "원 단위로 입력해야 합니다.";

    // 로또 번호 관련
    public static final String INVALID_LOTTO_NUMBER_FORMAT = "[ERROR] 로또 번호는 숫자로 입력해야 합니다.";
    public static final String INVALID_LOTTO_NUMBER_SIZE = "[ERROR] 로또 번호는 6개여야 합니다.";
    public static final String INVALID_LOTTO_NUMBER_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String INVALID_LOTTO_NUMBER_DUPLICATE = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    // 당첨 번호 관련
    public static final String INVALID_WINNING_NUMBER_FORMAT = "[ERROR] 당첨 번호는 숫자로 입력해야 합니다.";

    // 보너스 번호 관련
    public static final String INVALID_BONUS_NUMBER_FORMAT = "[ERROR] 보너스 번호는 숫자로 입력해야 합니다.";
    public static final String INVALID_BONUS_NUMBER_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";


}
