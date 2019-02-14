package poker;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static poker.HandOfPokerEnum.*;

@EqualsAndHashCode
public class Hand {
    private final List<Card> cards;    //手札

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
        Card firstCard = cards.get(0);  //手札の1枚目のカード(比較元)
        return cards.stream()
                .allMatch(card -> card.getSuit() == firstCard.getSuit());   //比較元と比較先のカードで全てマークが一致したらtrue
    }

    //1ペアかどうか判断するメソッド
    public boolean isOnePair() {
        return countTheNumberOfNCards(2, 1);    //重複しているランクの枚数:2,重複しているランクがいくつあるか:1
    }

    //3カードかどうか判断するメソッド
    public boolean isThreeOfAKind() {
        return countTheNumberOfNCards(3, 1);    //重複しているランクの枚数:3,重複しているランクがいくつあるか:1
    }

    //4カードかどうか判断するメソッド
    public boolean isFourOfAKind() {
        return countTheNumberOfNCards(4, 1);    //重複しているランクの枚数:4,重複しているランクがいくつあるか:1
    }

    //2ペアかどうか判断するメソッド
    public boolean isTwoPair() {
        return countTheNumberOfNCards(2, 2);    //重複しているランクの枚数:2,重複しているランクがいくつあるか:2
    }

    //フルハウスかどうか判断するメソッド
    public boolean isFullHouse() {
        return countTheNumberOfNCards(3, 1) && countTheNumberOfNCards(2, 1);    //手札が3カードかつ1ペアを満たしている場合trueを返す
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

    //ストレートフラッシュかどうか判断するメソッド
    public boolean isStraightFlush() {
        return isStraight() && isFlush();   //ストレートとフラッシュを両方満たす場合trueを返す
    }

    //ロイヤルストレートフラッシュかどうか判断するメソッド
    public boolean isRoyalStraightFlush() {
        return isStraightFlush() && isTenStart();   //特殊なストレートフラッシュを満たす場合trueを返す(ストレートは10-J-Q-K-Aのみ)
    }

    //手札の役名を聞くメソッド(ハイカードの判定)
    public HandOfPokerEnum askTheNameOfHand() {
        if (isRoyalStraightFlush()) {
            return ROYAL_STRAIGHTFLUSH;
        } else if (isStraightFlush()) {
            return STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return FULL_HOUSE;
        } else if (isFlush()) {
            return FLUSH;
        } else if (isStraight()) {
            return STRAIGHT;
        } else if (isThreeOfAKind()) {
            return THREE_OF_A_KIND;
        } else if (isTwoPair()) {
            return TWO_PAIR;
        } else if (isOnePair()) {
            return ONE_PAIR;
        } else {
            return HIGH_CARDS;
        }
    }

    //手札の中にn枚組がいくつあるか計算する
    private boolean countTheNumberOfNCards(int numberOfDuplicating, int numberOfDuplicatingPairs) {
        //手札をカードのランクでグルーピングする
        Map<Rank, Long> groupingByRank = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return groupingByRank.entrySet()
                .stream()
                .filter(g -> g.getValue() == numberOfDuplicating)
                .count() == numberOfDuplicatingPairs;
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
        List<Card> sortedHand = cards.stream().sorted().collect(Collectors.toList());
        return sortedHand;
    }

    //ソートした手札の先頭をチェックするメソッド
    private boolean isTenStart() {
        return asRankPoints().get(0) == 10; //先頭が10から始まっているか確かめる
    }

}
