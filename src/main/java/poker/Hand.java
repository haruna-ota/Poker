package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Hand {
    private List<Card> cards;    //手札

    //コンストラクタ
    public Hand(List<Card> cards) {
        this.cards = sortHand(cards);
    }

    //toString　手札表示用
    @Override
    public String toString() {
        return cards.toString();
    }

    //フラッシュかどうか判定するメソッド
    public boolean isFlush() {
        int sameSuitCount = 0;  //手札に同じマークが何枚あるか数える
        for (Card card : cards) {
            if (cards.get(0).getSuit() == card.getSuit()) {   //2枚目以降が、1枚目のマークと同じかどうか確認
                sameSuitCount++;    //1枚目と同じ場合はカウントを増やす
            }
        }
        //手札5枚が全て同じマークだった場合trueを返す
        return sameSuitCount == 5;
    }

    //ワンペアかどうか判断するメソッド
    public boolean isOnePair() {
        return countTheNumberOfNCards(2) == 1;  //手札5枚のうち2枚1組が1つあった場合trueを返す
    }

    //3カードかどうか判断するメソッド
    public boolean isThreeOfAKind() {
        return countTheNumberOfNCards(3) == 1;  //手札5枚のうち3枚1組が1つあった場合trueを返す
    }

    //4カードかどうか判断するメソッド
    public boolean isFourOfAKind() {
        return countTheNumberOfNCards(4) == 1;  //手札5枚のうち4枚1組が1つあった場合trueを返す
    }

    //2ペアかどうか判断するメソッド
    public boolean isTwoPair() {
        return countTheNumberOfNCards(2) == 2;  //手札5枚のうち2枚1組が2つあった場合trueを返す
    }

    //フルハウスかどうか判断するメソッド
    public boolean isFullHouse() {
        return countTheNumberOfNCards(3) == 1 && countTheNumberOfNCards(2) == 1;    //手札が3カードかつ1ペアを満たしている場合trueを返す
    }

    //ストレートかどうか判断するメソッド
    public boolean isStraight() {
        List<Integer> rankPoints = asRankPoints();    //ランクのみの手札

        int trueCount = 0;  //隣り合うランクのカードかの判断用

        for (int i = 0; i < rankPoints.size() - 1; i++) {
            if (rankPoints.get(i).equals(rankPoints.get(i + 1) - 1)) {    //現在見ているカードが次カードのランクから1引いたものであればok(隣り合うランク)
                trueCount++;
            }
        }
        return trueCount == 4;
    }

    //手札の中にn枚組がいくつあるか計算する
    private int countTheNumberOfNCards(int numberOfTheSameRank) {

        List<Integer> rankPoints = asRankPoints();    //ランクのみの手札
        Set<Integer> keys = new HashSet<>(rankPoints);  //手札の中で重複しているランクをまとめたリスト

        Map<Integer, Integer> nMap = new HashMap<>();   //<Integer:ランク,Integer:枚数(count)>

        int count;  //重複しているランクを数える用
        for (int key : keys) {
            count = 0;
            for (int rank : rankPoints) {
                if (key == rank) {  //手札に同じランクが見つかったらカウントを増やす
                    count++;
                }
            }
            nMap.put(key, count);   //mapに追加する際に比較元の1枚を加える

        }

        //手札の重複ランクのセット数を数える
        int numberOfSameRankSet = 0;
        for (int value : nMap.values()) {
            if (value == numberOfTheSameRank) {
                numberOfSameRankSet++;
            }
        }
        return numberOfSameRankSet;
    }

    //手札を変換する(ランクポイントのみ)
    private List<Integer> asRankPoints() {
        List<Integer> rh = new ArrayList<>();

        for (Card card : cards) {
            rh.add(card.getRank().getCalculationPoint()); //ランクポイントだけを追加していく
        }
        return rh;
    }

    //手札をソートする(昇順)
    private List<Card> sortHand(List<Card> cards) {
        int length = cards.size();
        List<Card> sortedHand = new ArrayList<>();  //ソート済みの手札
        Card min;   //暫定最小ランク値のカード

        for (int i = 0; i < length; i++) {
            min = cards.get(0);
            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(j).compare(min)) {     //min(暫定の最小カード)の方が大きければtrueを返す
                    min = cards.get(j);  //暫定を入れ替える
                }
            }
            sortedHand.add(min);
            cards.remove(min);
        }
        return sortedHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cards);
    }
}
