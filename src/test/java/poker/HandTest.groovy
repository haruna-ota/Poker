package poker

import spock.lang.Specification

import static poker.CardSuitEnum.*

class HandTest extends Specification {

    //カードを生成する
    private def $(CardSuitEnum suit, int displayPoint) {
        return new Card(suit, new Rank(displayPoint))

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
        [$(H, 1), $(H, 2), $(D, 11), $(S, 12), $(C, 13)] | "[H-A, H-2, D-J, S-Q, C-K]"
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
        [$(H, 2), $(D, 2), $(H, 3), $(H, 4), $(D, 6)]  | true   //手札に同じランクが2枚(2の1ペア)
        [$(S, 2), $(C, 2), $(D, 2), $(S, 3), $(H, 3)]  | true   //手札に同じランクが2枚と3枚(3の1ペア)
        [$(H, 2), $(S, 2), $(C, 2), $(D, 3), $(H, 5)]  | false  //手札に同じランクが3枚
        [$(H, 2), $(D, 2), $(S, 2), $(C, 2), $(S, 13)] | false  //手札に同じランクが4枚
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]  | false  //手札に同じランクが0枚
    }

    //役判定のテスト(THREE_OF_A_KIND)
    def threeOfAKindTest() {
        expect:
        new Hand(cards).isThreeOfAKind() == exp

        where:
        cards                                          | exp
        [$(H, 3), $(D, 3), $(S, 3), $(H, 4), $(D, 5)]  | true   //手札に同じランクが3枚
        [$(S, 2), $(C, 2), $(D, 2), $(S, 3), $(H, 3)]  | true   //手札に同じランクが2枚と3枚
        [$(H, 2), $(D, 2), $(S, 2), $(C, 2), $(S, 13)] | false   //手札に同じランクが4枚
        [$(C, 2), $(D, 2), $(H, 3), $(S, 4), $(S, 5),] | false   //手札に同じランクが2枚
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]  | false  //手札に同じランクが0枚
    }

    //役判定のテスト(FOUR_OF_A_KIND)
    def fourOfAKindTest() {
        expect:
        new Hand(cards).isFourOfAKind() == exp

        where:
        cards                                          | exp
        [$(H, 2), $(D, 2), $(S, 2), $(C, 2), $(S, 11)] | true   //手札に同じランクが4枚
        [$(S, 2), $(C, 2), $(D, 2), $(H, 3), $(S, 3)]  | false  //手札に同じランクが2枚と3枚
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]  | false  //手札に同じランクが0枚
    }

    //役判定のテスト(TWO_PAIR)
    def twoPairTest() {
        expect:
        new Hand(cards).isTwoPair() == exp

        where:
        cards                                         | exp
        [$(S, 2), $(H, 2), $(D, 5), $(C, 5), $(H, 7)] | true   //2と5の2ペア
        [$(H, 2), $(S, 2), $(D, 2), $(C, 2), $(D, 3)] | false  //2と2の2ペア
        [$(H, 2), $(S, 3), $(D, 3), $(H, 4), $(H, 5)] | false  //3の1ペア
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)] | false  //0ペア
    }

    //役判定のテスト(FULL_HOUSE)
    def fullHouseTest() {
        expect:
        new Hand(cards).isFullHouse() == exp

        where:
        cards                                         | exp
        [$(H, 2), $(D, 2), $(S, 2), $(C, 3), $(H, 3)] | true   //2が3枚と3が2枚(3カードかつ1ペア)
        [$(D, 4), $(S, 4), $(C, 4), $(H, 4), $(D, 7)] | false  //4が4枚(4カード)
        [$(D, 4), $(H, 5), $(C, 4), $(H, 4), $(D, 7)] | false  //4が3枚(3カード)
        [$(H, 5), $(H, 7), $(C, 3), $(H, 6), $(S, 5)] | false  //5が2枚(1ペア)
        [$(H, 4), $(S, 4), $(H, 5), $(S, 5), $(S, 6)] | false  //4が2枚、5が2枚(2ペア)
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)] | false  //0ペア
    }

    //役判定のテスト(STRAIGHT)
    def straightTest() {
        expect:
        new Hand(cards).isStraight() == exp

        where:
        cards                                             | exp
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]     | true
        [$(H, 1), $(H, 10), $(H, 11), $(H, 12), $(H, 13)] | true
        [$(H, 1), $(H, 2), $(H, 11), $(H, 12), $(H, 13)]  | false
        [$(H, 2), $(H, 3), $(H, 5), $(H, 6), $(H, 7)]     | false
    }

    //手札のソート(手札のカードのランクの順番がバラバラでもきちんと役を満たすことを確認する)
    def sortTest() {
        expect:
        new Hand(cards).sortHand() == exp

        where:
        cards                                             | exp
        [$(H, 2), $(H, 6), $(H, 4), $(H, 3), $(H, 5)]     | [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]     //全てランクが同じ場合
        [$(H, 2), $(D, 2), $(S, 5), $(C, 2), $(S, 2)]     | [$(D, 2), $(C, 2), $(H, 2), $(S, 2), $(S, 5)]     //マーク違い,ランク重複ありの場合
        [$(H, 5), $(D, 3), $(C, 10), $(H, 1), $(S, 12)]   | [$(D, 3), $(H, 5), $(C, 10), $(S, 12), $(H, 1)]   //全てランク違い、マークも違う場合
        [$(S, 13), $(S, 11), $(H, 10), $(S, 10), $(S, 1)] | [$(H, 10), $(S, 10), $(S, 11), $(S, 13), $(S, 1)]   //全てランク違い、マークも違う場合(10,J,Aの並び)
    }
}
