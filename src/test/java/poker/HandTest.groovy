package poker

import spock.lang.Specification

import static poker.CardSuitEnum.*
import static poker.HandOfPokerEnum.FLUSH
import static poker.HandOfPokerEnum.FOUR_OF_A_KIND
import static poker.HandOfPokerEnum.FULL_HOUSE
import static poker.HandOfPokerEnum.HIGH_CARDS
import static poker.HandOfPokerEnum.ONE_PAIR
import static poker.HandOfPokerEnum.ROYAL_STRAIGHTFLUSH
import static poker.HandOfPokerEnum.STRAIGHT
import static poker.HandOfPokerEnum.STRAIGHT_FLUSH
import static poker.HandOfPokerEnum.THREE_OF_A_KIND
import static poker.HandOfPokerEnum.TWO_PAIR

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
        //Hand cards = new Hand(cards)
        //def cards = new Hand(cards)
        //cards.toString() == "[H-2, H-3]"
        //cards.toString() == exp
        new Hand(cards).toString() == exp

        where:
        cards                                            | exp
        [$(H, 2), $(D, 11), $(S, 12), $(C, 13), $(H, 1)] | "[H-2, D-J, S-Q, C-K, H-A]"
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
        [$(H, 2), $(D, 2), $(H, 4), $(D, 4), $(H, 6)]  | false  //手札に同じランクが2枚×2セット

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

    //役判定のテスト(STRAIGHT_FLUSH)
    def straightFlushTest() {
        expect:
        new Hand(cards).isStraightFlush() == exp

        where:
        cards                                             | exp
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]     | true    //ストレートもフラッシュも満たしている
        [$(S, 1), $(S, 2), $(S, 3), $(S, 4), $(S, 5)]     | false   //フラッシュのみ満たしている
        [$(D, 1), $(H, 10), $(H, 11), $(C, 12), $(D, 13)] | false   //ストレートのみ満たしている
        [$(H, 4), $(D, 4), $(D, 5), $(D, 6), $(H, 7)]     | false   //どちらも満たしていない
    }

    //役判定のテスト(ROYAL_STRAIGHT_FLUSH)
    def royalStraightFlushTest() {
        expect:
        new Hand(cards).isRoyalStraightFlush() == exp

        where:
        cards                                              | exp
        [$(S, 1), $(S, 10), $(S, 11), $(S, 12), $(S, 13)]  | true
        [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]      | false   //ストレートフラッシュのみ満たしている
        [$(D, 1), $(H, 10), $(H, 11), $(C, 12), $(D, 13)]  | false   //ストレートのみ満たしている
        [$(D, 1), $(D, 2), $(D, 3), $(D, 4), $(D, 5)]      | false   //フラッシュのみ満たしている
        [$(H, 10), $(D, 11), $(H, 12), $(D, 13), $(H, 13)] | false   //どれもも満たしていない
    }

    //手札の役名を聞くテスト（役判定のテスト(HIGH_CARDS)）
    def theNameOfHand() {
        expect:
        new Hand(cards).askTheNameOfHand() == exp

        where:
        cards                                             | exp
        [$(S, 1), $(S, 10), $(S, 11), $(S, 12), $(S, 13)] | ROYAL_STRAIGHTFLUSH
        [$(H, 9), $(H, 10), $(H, 11), $(H, 12), $(H, 13)] | STRAIGHT_FLUSH
        [$(H, 5), $(S, 5), $(C, 5), $(D, 5), $(D, 13)]    | FOUR_OF_A_KIND
        [$(S, 4), $(D, 4), $(H, 4), $(H, 11), $(D, 11)]   | FULL_HOUSE
        [$(H, 1), $(H, 2), $(H, 3), $(H, 4), $(H, 5)]     | FLUSH
        [$(H, 9), $(D, 10), $(S, 11), $(H, 12), $(C, 13)] | STRAIGHT
        [$(C, 1), $(H, 1), $(D, 1), $(S, 10), $(H, 12)]   | THREE_OF_A_KIND
        [$(D, 3), $(H, 3), $(H, 5), $(S, 11), $(C, 11)]   | TWO_PAIR
        [$(H, 1), $(S, 8), $(C, 8), $(D, 12), $(H, 13)]   | ONE_PAIR
        [$(H, 1), $(S, 2), $(D, 3), $(C, 4), $(D, 5)]     | HIGH_CARDS
    }

    //手札のソート(手札のカードのランクの順番がバラバラでもきちんと役を満たすことを確認する)
    def sortTest() {
        expect:
        new Hand(cards) == new Hand(exp)

        where:
        cards                                             | exp
        [$(H, 2), $(H, 6), $(H, 4), $(H, 3), $(H, 5)]     | [$(H, 2), $(H, 3), $(H, 4), $(H, 5), $(H, 6)]     //全てランクが同じ場合
        [$(H, 2), $(D, 2), $(S, 5), $(C, 2), $(S, 2)]     | [$(C, 2), $(D, 2), $(H, 2), $(S, 2), $(S, 5)]     //マーク違い,ランク重複ありの場合
        [$(H, 5), $(D, 3), $(C, 10), $(H, 1), $(S, 12)]   | [$(D, 3), $(H, 5), $(C, 10), $(S, 12), $(H, 1)]   //全てランク違い、マークも違う場合
        [$(S, 13), $(S, 11), $(H, 10), $(S, 10), $(S, 1)] | [$(H, 10), $(S, 10), $(S, 11), $(S, 13), $(S, 1)] //全てランク違い、マークも違う場合(10,J,Aの並び)
    }
}
