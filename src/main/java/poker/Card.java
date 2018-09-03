package poker;

public class Card {
    private CardSuitEnum suit;    //カードのマーク
    private int rank;             //カードのランク(エースの場合:1)
    private String rankName;         //カードのランクの名前(エースの場合:A)
    private int rankPoints;        //カードのランクに対するポイント(エースの場合:14)

    //コンストラクタ
    public Card(CardSuitEnum suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        this.rankName = this.toString();
        this.rankPoints = this.givePointsToTheCard();
    }

    //toString  カード表示用
    @Override
    public String toString() {
        if (rank == 1) {
            rankName = "A";
        } else if (rank == 11) {
            rankName = "J";
        } else if (rank == 12) {
            rankName = "Q";
        } else if (rank == 13) {
            rankName = "K";
        } else {
            rankName = String.valueOf(rank);
        }
        return suit.toString() + "-" + rankName;
    }

    //getter
    public CardSuitEnum getSuit() {
        return suit;
    }

    //getter
    public int getRankPoints() {
        return rankPoints;
    }

    //カードにポイントをつける
    private int givePointsToTheCard() {
        if (rank == 1) {    //ランクが1のカード(A)は14(一番強いカード)として考える
            rankPoints = 14;
        } else {
            rankPoints = rank;
        }
        return rankPoints;
    }
}
