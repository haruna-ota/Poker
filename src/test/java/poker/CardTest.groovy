package poker

import spock.lang.Specification

class CardTest extends Specification {
    def toStringCard() {
        expect:
        new Card(suit, new Rank(displayPoint)).toString() == exp

        where:
        suit           | displayPoint | exp
        CardSuitEnum.H | 2            | "H-2"
        CardSuitEnum.S | 3            | "S-3"
        CardSuitEnum.D | 4            | "D-4"
        CardSuitEnum.C | 13           | "C-K"
        CardSuitEnum.H | 1            | "H-A"
    }
}
