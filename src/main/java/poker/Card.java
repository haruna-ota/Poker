package poker;

public class Card {
    private CardSuitEnum suit;    //カードのマーク
    private int rank;             //カードのランク

    //コンストラクタ
    public Card(CardSuitEnum suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    //toString  カード表示用
    @Override
    public String toString() {
        return suit.toString() + "-" + rank;
    }

    //getter
    public CardSuitEnum getSuit() {
        return suit;
    }

    //getter
    public int getRank(){
        return rank;
    }
}
