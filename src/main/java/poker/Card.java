package poker;

import java.util.Objects;

import static poker.CardSuitEnum.*;

public class Card implements Comparable<Card> {
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

    //カード同士を比べてどちらが大きいか判断するメソッド（スートの強弱:S>H>D>C）
    //比較元のカード <　比較されるカードの場合trueを返す
    @Override
    public int compareTo(Card other) {
        if (this.getRank().getCalculationPoint() == other.getRank().getCalculationPoint()) {
            //ランク同士が同じ場合のみスートで比べる
            if (this.getSuit() == S) {  //比較元のスートがスペードの場合
                return 1;
            } else if (this.getSuit() == H) {  //比較元のスートがハートの場合
                if (other.getSuit() == S) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.getSuit() == D) {  //比較元のスートがダイヤの場合
                if (other.getSuit() == S || other.getSuit() == H) {
                    return -1;
                } else {
                    return 1;
                }
            } else {  //比較元のカードのマークがクラブだった場合
                return -1;
            }
        } else {    //それ以外は、ランク(の持つポイント(計算用))を比べる
            if (this.getRank().getCalculationPoint() < other.getRank().getCalculationPoint()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
