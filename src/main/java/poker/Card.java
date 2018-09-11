package poker;

import java.util.Objects;

public class Card {
    private final CardSuitEnum suit;    //カードのマーク
    private final Rank rank;           //ランク（ランクのもつポイント(表示用)、ランク表示用の名前、ランクの持つポイント(計算用)）

    //コンストラクタ
    public Card(CardSuitEnum suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    //toString  カード表示用
    @Override
    public String toString() {
        return suit.toString() + "-" + rank.toString();
    }

    //getter
    public CardSuitEnum getSuit() {
        return suit;
    }

    //getter
    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                Objects.equals(rank, card.rank);
    }

    @Override
    public int hashCode() {

        return Objects.hash(suit, rank);
    }

    //カード同士を比べてどちらが大きいか判断するメソッド（スートの強弱:S>H>C>D）
    //比較元のカード <　比較されるカードの場合trueを返す
    public boolean compare(Card card) {
        if (this.getRank().getCalculationPoint() == card.getRank().getCalculationPoint()) {
            //ランク同士が同じであればスートを比べる
            if (this.getSuit() == CardSuitEnum.S) {   //比較元のカードのマークがスペードだった場合
                return false;
            } else if (this.getSuit() == CardSuitEnum.H) {  //比較元のカードのマークがハートだった場合
                return card.getSuit() == CardSuitEnum.S;
            } else if (this.getSuit() == CardSuitEnum.C) {  //比較元のカードのマークがクラブだった場合
                return card.getSuit() == CardSuitEnum.S || card.getSuit() == CardSuitEnum.H;
            } else {    //比較元のカードのマークがダイヤだった場合
                return true;
            }
        } else {    //それ以外は、ランク(の持つポイント(計算用))を比べる
            return this.getRank().getCalculationPoint() < card.getRank().getCalculationPoint();
        }
    }
}
