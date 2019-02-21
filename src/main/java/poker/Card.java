package poker;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Card implements Comparable<Card> {
    @Getter
    private final CardSuitEnum suit;    //カードのマーク
    @Getter
    private final Rank rank;           //ランク（ランクのもつポイント(表示用)、ランク表示用の名前、ランクの持つポイント(計算用)）

    //toString  カード表示用
    @Override
    public String toString() {
        return suit.toString() + "-" + rank.toString();
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
