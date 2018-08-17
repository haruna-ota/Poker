package poker;

public enum HandOfPokerEnum {
    //ポーカの役(強い順:10役)
    ROYAL_STRAIGHTFLUSH("ロイヤルストレートフラッシュ"),
    STRAIGHT_FLUSH("ストレートフラッシュ"),
    FOUR_OF_A_KIND("4カード"),
    FULL_HOUSE("フルハウス"),
    FLUSH("フラッシュ"),
    STRAIGHT("ストレート"),
    THREE_OF_A_KIND("3カード"),
    TWO_PAIR("2ペア"),
    ONE_PAIR("1ペア"),
    HIGH_CARDS("ハイカード");

    private String name;    //役名

    //コンストラクタ
    private HandOfPokerEnum(String name) {
        this.name = name;
    }


}
