package lotto.constants;

/**
 * 전역에서 사용하는 상수를 정의한 클래스
 */
public final class LottoConstants {
    // 인스턴스화 방지
    private LottoConstants() {
    }

    // 로또 번호 관련
    public static final int LOTTO_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    // 가격 롼련
    public static final int LOTTO_PRICE = 1000;

    // 구분자 관련
    public static final String DELEMITER = ",";
}
