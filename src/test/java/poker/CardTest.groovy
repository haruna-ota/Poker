package poker

import spock.lang.Specification

class CardTest extends Specification {
    def toStringCard() {
        expect:
        new Card(suit, rank).toString() == exp

        where:
        suit           | rank | exp
        CardSuitEnum.H | 2    | "H-2"
        CardSuitEnum.S | 3    | "S-3"
        CardSuitEnum.D | 4    | "D-4"
        CardSuitEnum.C | 13   | "C-13"
        CardSuitEnum.H | 1    | "H-1"
    }
}
