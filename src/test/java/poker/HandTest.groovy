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
        [$(H, 3), $(D, 2), $(H, 4), $(H, 2), $(D, 6)]  | true   //手札に同じランクが2枚
        [$(H, 2), $(D, 3), $(H, 5), $(S, 2), $(C, 2)]  | true   //手札に同じランクが3枚
        [$(H, 3), $(D, 2), $(S, 3), $(S, 2), $(C, 2)]  | true   //手札に同じランクが2枚と3枚
        [$(H, 2), $(D, 2), $(S, 13), $(S, 2), $(C, 2)] | true   //手札に同じランクが4枚
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]  | false  //手札に同じランクが0枚
    }

    //役判定のテスト(THREE_OF_A_KIND)
    def threeOfAKindTest() {
        expect:
        new Hand(cards).isThreeOfAKind() == exp

        where:
        cards                                          | exp
        [$(H, 3), $(D, 3), $(S, 3), $(H, 4), $(D, 5)]  | true   //手札に同じランクが3枚
        [$(H, 3), $(D, 2), $(S, 3), $(S, 2), $(C, 2)]  | true   //手札に同じランクが2枚と3枚
        [$(H, 2), $(D, 2), $(S, 13), $(S, 2), $(C, 2)] | true   //手札に同じランクが4枚
        [$(H, 3), $(D, 2), $(S, 4), $(S, 5), $(C, 2)]  | false   //手札に同じランクが2枚
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]  | false  //手札に同じランクが0枚
    }

    //役判定のテスト(FOUR_OF_A_KIND)
    def fourOfAKindTest() {
        expect:
        new Hand(cards).isFourOfAKind() == exp

        where:
        cards                                          | exp
        [$(H, 2), $(D, 2), $(S, 2), $(S, 11), $(C, 2)] | true   //手札に同じランクが4枚
        [$(H, 3), $(D, 2), $(S, 3), $(S, 2), $(C, 2)]  | false  //手札に同じランクが2枚と3枚
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]  | false  //手札に同じランクが0枚
    }

    //役判定のテスト(TWO_PAIR)
    def twoPairTest() {
        expect:
        new Hand(cards).isTwoPair() == exp

        where:
        cards                                         | exp
        [$(H, 7), $(D, 5), $(H, 2), $(C, 5), $(S, 2)] | true   //2と5の2ペア
        [$(H, 2), $(S, 2), $(D, 3), $(D, 2), $(C, 2)] | true   //2と2の2ペア
        [$(H, 4), $(H, 5), $(S, 3), $(H, 2), $(D, 3)] | false  //3の1ペア
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)] | false  //0ペア
    }
}
