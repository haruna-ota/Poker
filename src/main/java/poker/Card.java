package poker;

import java.util.Objects;

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
        if (this.getRank().compareTo(other.getRank()) == 0) {
            return this.getSuit().compareTo(other.getSuit());
        } else {
            return this.getRank().compareTo(other.getRank());
        }
    }
}
