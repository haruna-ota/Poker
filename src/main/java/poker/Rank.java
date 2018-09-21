package poker;

import java.util.Objects;

public class Rank implements Comparable<Rank> {
    private final int displayPoint;     //カードのランクに対するポイント(表示用)(ex.エースの場合:1)
    private final String name;          //カードのランクの名前(表示用)(ex.エースの場合:A)
    private final int calculationPoint; //カードのランクに対するポイント(計算用)(ex.エースの場合:14)

    //コンストラクタ
    public Rank(int displayPoint) {
        this.displayPoint = displayPoint;

        //カードに表示用のランクの名前をつける
        if (displayPoint == 1) {
            this.name = "A";
        } else if (displayPoint == 11) {
            this.name = "J";
        } else if (displayPoint == 12) {
            this.name = "Q";
        } else if (displayPoint == 13) {
            this.name = "K";
        } else {
            this.name = String.valueOf(displayPoint);
        }

        //カードにランクに対するポイントをつける
        if (displayPoint == 1) {    //ランクが1のカード(A)は14(一番強いカード)として考える
            this.calculationPoint = 14;
        } else {
            this.calculationPoint = displayPoint;
        }
    }

    //toString  ランク表示用
    @Override
    public String toString() {
        return name;
    }

    //getter
    public int getDisplayPoint() {
        return displayPoint;
    }

    //getter
    public int getCalculationPoint() {
        return calculationPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return displayPoint == rank.displayPoint &&
                calculationPoint == rank.calculationPoint &&
                Objects.equals(name, rank.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(displayPoint, name, calculationPoint);
    }

    @Override
    public int compareTo(Rank other) {
        return Integer.compare(this.calculationPoint, other.calculationPoint);
    }
}
