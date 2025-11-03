package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("구입 금액이 1000원 미만일 때 예외")
    @Test
    void 구입금액_최소금액_미만_예외() {
        assertSimpleTest(() -> {
            runException("500");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 때 예외")
    @Test
    void 구입금액_단위_예외() {
        assertSimpleTest(() -> {
            runException("1500");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호가 6개가 아닐 때 예외")
    @Test
    void 당첨번호_개수_예외() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호에 중복이 있을 때 예외")
    @Test
    void 당첨번호_중복_예외() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,5");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호 범위가 1~45를 벗어날 때 예외")
    @Test
    void 당첨번호_범위_예외() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,46");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복될 때 예외")
    @Test
    void 보너스번호_중복_예외() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "6");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호 범위가 1~45를 벗어날 때 예외")
    @Test
    void 보너스번호_범위_예외() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "46");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("1등 당첨 테스트")
    @Test
    void 일등_당첨() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "6개 일치 (2,000,000,000원) - 1개",
                            "총 수익률은 200000000.0%입니다."
                    );
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @DisplayName("2등 당첨 테스트 - 5개 일치 + 보너스")
    @Test
    void 이등_당첨() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                            "총 수익률은 3000000.0%입니다."
                    );
                },
                List.of(1, 2, 3, 4, 5, 7)
        );
    }

    @DisplayName("3등 당첨 테스트 - 5개 일치")
    @Test
    void 삼등_당첨() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "5개 일치 (1,500,000원) - 1개",
                            "총 수익률은 150000.0%입니다."
                    );
                },
                List.of(1, 2, 3, 4, 5, 8)
        );
    }

    @DisplayName("낙첨 테스트")
    @Test
    void 낙첨() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("1000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "총 수익률은 0.0%입니다."
                    );
                },
                List.of(10, 11, 12, 13, 14, 15)
        );
    }

    @DisplayName("여러 로또 구매 및 혼합 당첨")
    @Test
    void 여러_로또_혼합당첨() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("3000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "3개를 구매했습니다.",
                            "5개 일치 (1,500,000원) - 1개",
                            "4개 일치 (50,000원) - 1개",
                            "3개 일치 (5,000원) - 1개"
                    );
                },
                List.of(1, 2, 3, 4, 5, 10),  // 5개 일치 (3등)
                List.of(1, 2, 3, 4, 10, 11), // 4개 일치 (4등)
                List.of(1, 2, 3, 10, 11, 12) // 3개 일치 (5등)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
