package poker

import spock.lang.Specification

import static poker.CardSuitEnum.*

class HandTest extends Specification {

    //カードを生成する
    private def $(CardSuitEnum suit, int rank) {
        return new Card(suit, rank)

    }

    //手札のtoStringのテスト(表示)
    def toStringTest() {
        expect:
        //List<Card> cards = Arrays.asList($(CardSuitEnum.H, 2), $(CardSuitEnum.H, 3))
        //def cards = [$(H, 2), $(H, 3)]
        //Hand hand = new Hand(cards)
        //def hand = new Hand(cards)
        //hand.toString() == "[H-2, H-3]"
        //hand.toString() == exp
        new Hand(cards).toString() == exp

        where:
        cards                                            | exp
        [$(H, 1), $(H, 2), $(D, 11), $(S, 12), $(C, 13)] | "[H-1, H-2, D-11, S-12, C-13]"
    }

    //役判定のテスト(FLUSH)
    def flushTest() {
        expect:
        new Hand(cards).isFlush() == exp

        where:
        cards                                         | exp
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)] | true    //ストレートも含
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(D, 6)] | false

    }

    //役判定のテスト(ONE_PAIR)
    def onePairTest() {
        expect:
        new Hand(cards).isOnePair() == exp

        where:
        cards                                          | exp
        [$(H, 3), $(D, 2), $(H, 4), $(H, 2), $(D, 6)]  | true
        [$(H, 2), $(D, 3), $(H, 5), $(S, 2), $(C, 2)]  | true
        [$(H, 2), $(D, 2), $(S, 13), $(S, 2), $(C, 2)] | true
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]  | false
    }
}
