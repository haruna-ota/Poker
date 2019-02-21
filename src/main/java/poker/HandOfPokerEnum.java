package poker;

import lombok.AllArgsConstructor;

@AllArgsConstructor
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

    private final String name;    //役名

    //toString（手札の役名表示用）
    @Override
    public String toString() {
        return name;
    }
}
