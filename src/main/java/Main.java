import poker.Card;
import poker.Hand;
import poker.Rank;

import java.util.Arrays;
import java.util.List;

import static poker.CardSuitEnum.*;

public class Main {
    public static void main(String[] args) {
        //手札を生成する
        //手札1:ロイヤルストレートフラッシュ
        List<Card> cards1 = Arrays.asList(
                new Card(S, new Rank(1)),
                new Card(S, new Rank(10)),
                new Card(S, new Rank(11)),
                new Card(S, new Rank(12)),
                new Card(S, new Rank(13))
        );

        Hand hand1 = new Hand(cards1);
        //Stringと+してプリントしhているので、自動的にtoStringが使われる(ex.) hand1.askTheNameOfHand.toStringと一緒の意味)
        System.out.println(hand1 + "\n" + "この手札の役名は:" + hand1.askTheNameOfHand() + "\n");


        //手札2:ストレートフラッシュ
        List<Card> cards2 = Arrays.asList(
                new Card(H, new Rank(9)),
                new Card(H, new Rank(10)),
                new Card(H, new Rank(11)),
                new Card(H, new Rank(12)),
                new Card(H, new Rank(13))
        );

        Hand hand2 = new Hand(cards2);
        System.out.println(hand2 + "\n" + "この手札の役名は:" + hand2.askTheNameOfHand() + "\n");


        //手札3:4カード
        List<Card> cards3 = Arrays.asList(
                new Card(H, new Rank(5)),
                new Card(S, new Rank(5)),
                new Card(C, new Rank(5)),
                new Card(D, new Rank(5)),
                new Card(D, new Rank(13))
        );

        Hand hand3 = new Hand(cards3);
        System.out.println(hand3 + "\n" + "この手札の役名は:" + hand3.askTheNameOfHand() + "\n");


        //手札4:フルハウス
        List<Card> cards4 = Arrays.asList(
                new Card(S, new Rank(4)),
                new Card(D, new Rank(4)),
                new Card(H, new Rank(4)),
                new Card(H, new Rank(11)),
                new Card(D, new Rank(11))
        );

        Hand hand4 = new Hand(cards4);
        System.out.println(hand4 + "\n" + "この手札の役名は:" + hand4.askTheNameOfHand() + "\n");


        //手札5:フラッシュ
        List<Card> cards5 = Arrays.asList(
                new Card(H, new Rank(1)),
                new Card(H, new Rank(2)),
                new Card(H, new Rank(3)),
                new Card(H, new Rank(4)),
                new Card(H, new Rank(5))
        );

        Hand hand5 = new Hand(cards5);
        System.out.println(hand5 + "\n" + "この手札の役名は:" + hand5.askTheNameOfHand() + "\n");


        //手札6:ストレート
        List<Card> cards6 = Arrays.asList(
                new Card(H, new Rank(9)),
                new Card(D, new Rank(10)),
                new Card(S, new Rank(11)),
                new Card(H, new Rank(12)),
                new Card(C, new Rank(13))
        );

        Hand hand6 = new Hand(cards6);
        System.out.println(hand6 + "\n" + "この手札の役名は:" + hand6.askTheNameOfHand() + "\n");


        //手札7:3カード
        List<Card> cards7 = Arrays.asList(
                new Card(C, new Rank(1)),
                new Card(H, new Rank(1)),
                new Card(D, new Rank(1)),
                new Card(S, new Rank(10)),
                new Card(H, new Rank(12))
        );

        Hand hand7 = new Hand(cards7);
        System.out.println(hand7 + "\n" + "この手札の役名は:" + hand7.askTheNameOfHand() + "\n");


        //手札8:2ペア
        List<Card> cards8 = Arrays.asList(
                new Card(D, new Rank(3)),
                new Card(H, new Rank(3)),
                new Card(H, new Rank(5)),
                new Card(S, new Rank(11)),
                new Card(C, new Rank(11))
        );

        Hand hand8 = new Hand(cards8);
        System.out.println(hand8 + "\n" + "この手札の役名は:" + hand8.askTheNameOfHand() + "\n");


        //手札9:1ペア
        List<Card> cards9 = Arrays.asList(
                new Card(H, new Rank(1)),
                new Card(S, new Rank(8)),
                new Card(C, new Rank(8)),
                new Card(D, new Rank(12)),
                new Card(H, new Rank(13))
        );

        Hand hand9 = new Hand(cards9);
        System.out.println(hand9 + "\n" + "この手札の役名は:" + hand9.askTheNameOfHand() + "\n");


        //手札10:ハイカード
        List<Card> cards10 = Arrays.asList(
                new Card(H, new Rank(1)),
                new Card(S, new Rank(2)),
                new Card(D, new Rank(3)),
                new Card(C, new Rank(4)),
                new Card(D, new Rank(5))
        );

        Hand hand10 = new Hand(cards10);
        System.out.println(hand10 + "\n" + "この手札の役名は:" + hand10.askTheNameOfHand() + "\n");
    }
}
