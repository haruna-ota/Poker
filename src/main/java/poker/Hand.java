package poker;

import java.util.List;

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
        int sameRankCount = 1;  //手札に任意のランクが何枚あるか数える
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) { //一個ずらしたものから比較を始める
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    sameRankCount++;
                }
            }
            if (sameRankCount >= 2) {   //同じランクが2枚以上になった場合、確認終了
                break;
            }
        }
        return sameRankCount >= 2;  //手札5枚のうち2枚以上が同じランクだった場合trueを返す
    }
}
