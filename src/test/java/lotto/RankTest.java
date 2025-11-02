package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @DisplayName("6개 일치가 일치한다.")
    @Test
    void matchSixNumbers() {
        Rank rank = Rank.of(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스가 일치한다.")
    @Test
    void matchFiveNumbersWithBonus() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스가 일치하지 않는다.")
    @Test
    void matchFiveNumbersWithoutBonus() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개 일치한다.")
    @Test
    void matchFourNumbers() {
        Rank rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개 일치하면 5등이다")
    @Test
    void matchThreeNumbers() {
        Rank rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("2개 이하 일치한다.")
    @ParameterizedTest
    @CsvSource({"0", "1", "2"})
    void matchLessThanThree(int matchCount) {
        Rank rank = Rank.of(matchCount, false);
        assertThat(rank).isEqualTo(Rank.NONE);
    }

    @DisplayName("상금을 반환한다")
    @Test
    void getPrize() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000);
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000);
        assertThat(Rank.NONE.getPrize()).isEqualTo(0);
    }

    @DisplayName("당첨 여부를 확인한다")
    @Test
    void isWinning() {
        assertThat(Rank.FIRST.isWinning()).isTrue();
        assertThat(Rank.SECOND.isWinning()).isTrue();
        assertThat(Rank.NONE.isWinning()).isFalse();
    }
}
