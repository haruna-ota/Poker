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

    def compareTest() {
        expect:
        new Card(suit1, new Rank(displayPoint1)).compareTo(new Card(suit2, new Rank(displayPoint2))) == exp

        where:
        suit1          | displayPoint1 | suit2          | displayPoint2 | exp
        CardSuitEnum.S | 2             | CardSuitEnum.S | 3             | -1    //スートが同じ場合(2<3)
        CardSuitEnum.D | 5             | CardSuitEnum.H | 5             | -1    //ランクが同じ場合(D<H)
        CardSuitEnum.H | 13            | CardSuitEnum.C | 13            | 1     //ランクが同じ場合(H>C)
        CardSuitEnum.D | 1             | CardSuitEnum.S | 13            | 1    //スートもランクも違う場合(13<1)
    }
}
