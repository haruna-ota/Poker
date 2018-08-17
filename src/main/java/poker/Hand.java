package poker;

import java.util.List;

public class Hand {
    private List<Card> hand;    //手札
    private HandOfPokerEnum name;   //役名

    //コンストラクタ
    public Hand(List<Card> hand) {
        this.hand = hand;
        this.name = HandOfPokerEnum.FLUSH;
    }

    //toString　手札表示用
    @Override
    public String toString() {
        return hand.toString();
    }

    //役名を答えるメソッド
    public String answerTheNameOfHand() {
        //ここに条件分岐を入れて最終的な役名を探す（考えられる役の中で、最も強いものを選ぶ）
        return name.toString(); //最終的な役名を答える
    }

    //フラッシュかどうか判定するメソッド
    public boolean isFlush() {
        int sameSuitCount = 0;  //手札に同じマークが何枚あるか数える
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(0).toString().equals(hand.get(i).toString())) {
                sameSuitCount++;
            }
        }

        //手札5枚が全て同じマークだった場合
        return sameSuitCount == 5;
    }

}
