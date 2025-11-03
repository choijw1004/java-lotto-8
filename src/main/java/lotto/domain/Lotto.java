package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoNumberValidator.validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    /**
     * 로또 번호 리스트를 외부에서 수정할 수 없도록 반환하는 메서드
     *
     * @return 로또 번호의 불변 리스트
     */
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
