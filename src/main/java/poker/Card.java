package poker;

public class Card {
    private final CardSuitEnum suit;    //カードのマーク
    private final Rank rank;           //ランク（カードのランク、ランク表示用の名前、ランクの持つポイント）

    //コンストラクタ
    public Card(CardSuitEnum suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    //toString  カード表示用
    @Override
    public String toString() {
        return suit.toString() + "-" + rank.getName();
    }

    //getter
    public CardSuitEnum getSuit() {
        return suit;
    }

    //getter
    public Rank getRank() {
        return rank;
    }
}
