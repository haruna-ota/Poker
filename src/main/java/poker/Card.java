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
    public int getRank() {
        return givePointsToTheCard();
    }

    //カードにポイントをつける
    private int givePointsToTheCard() {
        int rankPoint;
        if (rank == 1) {    //ランクが1のカード(A)は14(一番強いカード)として考える
            rankPoint = 14;
        } else {
            rankPoint = rank;
        }
        return rankPoint;
    }
}
