package poker

import spock.lang.Specification

class CardTest extends Specification {
    def toStringCard() {
        expect:
        new Card(suit, rank).toString() == exp

        where:
        suit               | rank | exp
        CardSuitEnum.HEART | 2    | "HEART-2"
        CardSuitEnum.HEART | 3    | "HEART-3"
        CardSuitEnum.HEART | 4    | "HEART-4"
        CardSuitEnum.HEART | 5    | "HEART-5"
        CardSuitEnum.HEART | 6    | "HEART-6"
    }
}
