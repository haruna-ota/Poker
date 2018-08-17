package poker

import spock.lang.Specification

import static poker.CardSuitEnum.HEART

class HandTest extends Specification {
    def toStringTest() {
        expect:
        //List<Card> cards = Arrays.asList(new Card(CardSuitEnum.HEART, 2), new Card(CardSuitEnum.HEART, 3))
        //def cards = [new Card(HEART, 2), new Card(HEART, 3)]
        //Hand hand = new Hand(cards)
        //def hand = new Hand(cards)
        //hand.toString() == "[HEART-2, HEART-3]"
        //hand.toString() == exp
        new Hand(cards).toString() == exp

        where:
        cards                                    | exp
        [new Card(HEART, 2), new Card(HEART, 3)] | "[HEART-2, HEART-3]"
    }
}
