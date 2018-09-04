package poker;

public class Card {
    private final CardSuitEnum suit;    //カードのマーク
    private final int rank;             //カードのランク(エースの場合:1)
    private final String rankName;      //カードのランクの名前(表示用)(エースの場合:A)
    private final int rankPoint;        //カードのランクに対するポイント(エースの場合:14)

    //コンストラクタ
    public Card(CardSuitEnum suit, int rank) {
        this.suit = suit;
        this.rank = rank;

        //カードに表示用のランクの名前をつける
        if (rank == 1) {
            this.rankName = "A";
        } else if (rank == 11) {
            this.rankName = "J";
        } else if (rank == 12) {
            this.rankName = "Q";
        } else if (rank == 13) {
            this.rankName = "K";
        } else {
            this.rankName = String.valueOf(rank);
        }

        //カードにランクに対するポイントをつける
        if (rank == 1) {    //ランクが1のカード(A)は14(一番強いカード)として考える
            this.rankPoint = 14;
        } else {
            this.rankPoint = rank;
        }
    }

    //toString  カード表示用
    @Override
    public String toString() {
        return suit.toString() + "-" + rankName;
    }

    //getter
    public CardSuitEnum getSuit() {
        return suit;
    }

    //getter
    public int getRankPoint() {
        return rankPoint;
    }
}
