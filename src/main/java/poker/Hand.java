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
}
