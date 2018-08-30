package poker;

import java.util.ArrayList;
import java.util.Arrays;
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
        return countTheNumberOfPairs() >= 1;  //手札5枚のうちペアカードが1組以上あった場合trueを返す
    }

    //3カードかどうか判断するメソッド
    public boolean isThreeOfAKind() {
        return countTheNumberOfThreeOfAKind() == 1;  //手札5枚のうち3枚以上が同じランクだった場合trueを返す
    }

    //4カードかどうか判断するメソッド
    public boolean isFourOfAKind() {
        return countTheNumberOfNCards(4) == 1;  //手札5枚のうちペアカードが1組以上あった場合trueを返す


    }

    //2ペアかどうか判断するメソッド
    public boolean isTwoPair() {
        return countTheNumberOfPairs() == 2;  //手札5枚のうちペアカードが1組以上あった場合trueを返す

    }

    //手札に任意のランクが何枚あるか数えるメソッド(最大値)
    private int countTheNumberOfSameRankCard() {
        int sameRankCount = 0;  //手札に任意のランクが何枚あるか数える
        int sameRankCountOfMax = 0;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) { //一個ずらしたものから比較を始める
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    sameRankCount++;
                    if (sameRankCount > sameRankCountOfMax) {   //値の更新(最大枚数をとる)
                        sameRankCountOfMax = sameRankCount;
                    }
                }
            }
            sameRankCount = 0;  //jが1週周りきったらカウントリセット
        }
        if (sameRankCountOfMax > 0) {   //手札に同じランクが1枚以上あった場合、比較元の1枚を加える
            sameRankCountOfMax = sameRankCountOfMax + 1;
        }
        return sameRankCountOfMax;
    }

    //手札にペアがいくつあるか計算する
    private int countTheNumberOfPairs() {
        int sameRankCount = 0;
        int numberOfPairs = 0;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) { //一個ずらしたものから比較を始める
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    sameRankCount++;
                    if (sameRankCount == 2) {   //同じランクが2枚見つかったら
                        sameRankCount = 0;      //カウントリセット（ペア1組出来上がり済）
                    }
                }
            }
            if (sameRankCount > 0) {
                numberOfPairs++;
            }
            sameRankCount = 0;  //jが1週周りきったらカウントリセット
        }
        return numberOfPairs;
    }

    //手札の中に2枚組がいくつあるか計算する
    public int countTheNumberOfNCards(int n) {
        int sameRankCount = 0;
        int numberOfPairs = 0;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    sameRankCount++;
                }
            }
            if (sameRankCount == n - 1) {
                numberOfPairs++;
            }
            sameRankCount = 0;
        }
        System.out.println(numberOfPairs);
        return numberOfPairs;
    }

    //手札の中に2枚組がいくつあるか計算する
    public int countTheNumberOfTwoPairs() {
        int sameRankCount = 0;
        int numberOfPairs = 0;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    sameRankCount++;
                }
            }
            if (sameRankCount == 1) {
                numberOfPairs++;
            }
            sameRankCount = 0;
        }
        System.out.println(numberOfPairs);
        return numberOfPairs;
    }

    //手札の中に3枚組がいくつあるか計算する
    public int countTheNumberOfThreeOfAKind() {
        int numberOfPairs = 0;

        List<Integer> ranks = asRanks();    //ランクのみの手札
        Set<Integer> keys = new HashSet<>(ranks);  //手札の中で重複しているランクをまとめたリスト

        Map<Integer, Integer> map3 = new HashMap<>();   //<Integer:ランク,Integer:枚数(count)>

        int count;
        System.out.println(keys);
        for (int key : keys) {
            count = 0;
            for (int rank : ranks) {
                if (key == rank) {  //手札に同じランクが見つかったらカウントを増やす
                    count++;
                }
            }
            //System.out.println(count);
            map3.put(key, count);//mapに追加する際に比較元の1枚を加える
            //System.out.println(map3);
        }

        System.out.println(map3);
        if (map3.values().contains(3)) {    //同じランクが3枚揃っている場合(countが3のものが含まれていたら)
            numberOfPairs++;    //3枚組が1つ完成
        }
        //System.out.println(numberOfPairs);

        return numberOfPairs;
    }

    //手札の中に4枚組がいくつあるか計算する
    public int countTheNumberOfFourOfAKind() {
        int sameRankCount = 0;
        int numberOfPairs = 0;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    sameRankCount++;
                }
            }
            if (sameRankCount == 3) {
                numberOfPairs++;
            }
            sameRankCount = 0;
        }
        System.out.println(numberOfPairs);
        return numberOfPairs;
    }

    //手札を変換する(ランクのみ)
    private List<Integer> asRanks() {
        List<Integer> rh = new ArrayList<>();

        for (Card card : hand) {
            rh.add(card.getRank());
        }
        System.out.println(rh);
        return rh;
    }

}
