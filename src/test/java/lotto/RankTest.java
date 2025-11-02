package lotto;

import lotto.domain.Rank;
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
        assertThat(rank).isEqualTo(Rank.MATCH_6);
    }

    @DisplayName("5개 일치하고 보너스가 일치한다.")
    @Test
    void matchFiveNumbersWithBonus() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.MATCH_5_BONUS);
    }

    @DisplayName("5개 일치하고 보너스가 일치하지 않는다.")
    @Test
    void matchFiveNumbersWithoutBonus() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.MATCH_5);
    }

    @DisplayName("4개 일치한다.")
    @Test
    void matchFourNumbers() {
        Rank rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.MATCH_4);
    }

    @DisplayName("3개 일치하면 5등이다")
    @Test
    void matchThreeNumbers() {
        Rank rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.MATCH_3);
    }

    @DisplayName("2개 이하 일치한다.")
    @ParameterizedTest
    @CsvSource({"0", "1", "2"})
    void matchLessThanThree(int matchCount) {
        Rank rank = Rank.of(matchCount, false);
        assertThat(rank).isEqualTo(Rank.NO_MATCH);
    }

    @DisplayName("상금을 정상 반환한다")
    @Test
    void getPrize() {
        assertThat(Rank.MATCH_6.getPrize()).isEqualTo(2_000_000_000);
        assertThat(Rank.MATCH_5_BONUS.getPrize()).isEqualTo(30_000_000);
        assertThat(Rank.MATCH_5.getPrize()).isEqualTo(1_500_000);
        assertThat(Rank.MATCH_4.getPrize()).isEqualTo(50_000);
        assertThat(Rank.MATCH_3.getPrize()).isEqualTo(5_000);
        assertThat(Rank.NO_MATCH.getPrize()).isEqualTo(0);
    }

    @DisplayName("당첨 여부를 확인한다")
    @Test
    void isWinning() {
        assertThat(Rank.MATCH_6.isWinning()).isTrue();
        assertThat(Rank.MATCH_5_BONUS.isWinning()).isTrue();
        assertThat(Rank.MATCH_5.isWinning()).isTrue();
        assertThat(Rank.MATCH_4.isWinning()).isTrue();
        assertThat(Rank.MATCH_3.isWinning()).isTrue();
        assertThat(Rank.NO_MATCH.isWinning()).isFalse();
    }
}
