package lotto.domain;

public enum Rank {
    MATCH_6(6, false, 2_000_000_000, "6개 일치"),
    MATCH_5_BONUS(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    MATCH_5(5, false, 1_500_000, "5개 일치"),
    MATCH_4(4, false, 50_000, "4개 일치"),
    MATCH_3(3, false, 5_000, "3개 일치"),
    NO_MATCH(0, false, 0, "모두 불일치");

    private final int matchCount;
    private final boolean hasBonus;
    private final int prize;
    private final String message;

    Rank(int matchCount,
         boolean hasBonus,
         int prize,
         String message
    ) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
        this.message = message;
    }

    /**
     * 일치 개수와 보너스 일치 여부로 등수를 결정
     *
     * @param matchCount 일치한 번호 개수
     * @param hasBonus   보너스 번호 일치 여부
     * @return 해당하는 등수 (Rank)
     */
    public static Rank of(int matchCount, boolean hasBonus) {
        if (matchCount < MATCH_3.matchCount) {
            return NO_MATCH;
        }

        for (Rank rank : values()) {
            if (rank == NO_MATCH) {
                continue;
            }
            if (rank.matchCount == matchCount && rank.hasBonus) {
                return rank;
            }
        }

        return findByMatchCount(matchCount);
    }

    /**
     * 보너스 일치 여부와 관계없이 일치 개수만으로 등수를 찾음
     * 5개 일치 시 보너스가 없는 경우(3등)를 찾기 위해 사용
     *
     * @param matchCount 일치한 번호 개수
     * @return 해당하는 등수 (Rank)
     */
    private static Rank findByMatchCount(int matchCount) {
        for (Rank rank : values()) {
            if (rank.matchCount == matchCount && !rank.hasBonus) {
                return rank;
            }
        }
        return NO_MATCH;
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }

    public boolean isWinning() {
        return this != NO_MATCH;
    }
}