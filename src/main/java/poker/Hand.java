package poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hand {
    private List<Card> hand;    //手札

    //コンストラクタ
    public Hand(List<Card> hand) {
        this.hand = hand;
    }

    //toString　手札表示用
    @Override
    public String toString() {
        return hand.toString();
    }

    //フラッシュかどうか判定するメソッド
    public boolean isFlush() {
        int sameSuitCount = 0;  //手札に同じマークが何枚あるか数える
        for (Card Hand : hand) {
            if (hand.get(0).getSuit() == Hand.getSuit()) {   //2枚目以降が、1枚目のマークと同じかどうか確認
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

    //手札の中にn枚組がいくつあるか計算する
    private int countTheNumberOfNCards(int numberOfTheSameRank) {

        List<Integer> ranks = asRanks();    //ランクのみの手札
        Set<Integer> keys = new HashSet<>(ranks);  //手札の中で重複しているランクをまとめたリスト

        Map<Integer, Integer> nMap = new HashMap<>();   //<Integer:ランク,Integer:枚数(count)>

        int count;  //重複しているランクを数える用
        for (int key : keys) {
            count = 0;
            for (int rank : ranks) {
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

    //手札を変換する(ランクのみ)
    private List<Integer> asRanks() {
        List<Integer> rh = new ArrayList<>();

        for (Card card : hand) {
            rh.add(card.getRank()); //ランクだけを追加していく
        }
        return rh;
    }

}
