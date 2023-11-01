package shufflecard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    // 定义花色
    private static final String[] SUITS = {"♥","♠","♣","♦"};

    // 初始化出一副完整的扑克牌(无大小王)
    public static List<Card> buyCard(){
        List<Card> cards = new ArrayList<>(52);
        for(int i = 0; i < SUITS.length; i++){
            for(int j = 1; j <= 13; j++){
                Card card = new Card(SUITS[i], j);
                cards.add(card);
            }
        }
        return cards;
    }

    // 洗牌 ———— 随机数交换
    public static void shuffle(List<Card> cards){
        Random random = new Random();
        for(int i = cards.size() -1; i > 0; i--){
            int j = random.nextInt(i);
            // 交换
            Card tmp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, tmp);
        }
    }

    public static void main(String[] args) {
        // 初始化出一副牌
        List<Card> cards = buyCard();
        System.out.println(cards);
        // 洗牌
        System.out.println("洗牌：");
        shuffle(cards);
        System.out.println(cards);

        // 3个人，每个人轮流揭牌5张
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        List<Card> hand3 = new ArrayList<>();

        // 将上面3个人的牌集合存放在下面这个集合中，方便调用
        List<List<Card>> hand = new ArrayList<>();
        hand.add(hand1);
        hand.add(hand2);
        hand.add(hand3);

        // 三个人轮流揭牌，每个人手中要有5张牌
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                // 揭牌的动作
                Card card = cards.remove(0);
                // 如何翻到指定的人手里呢
                hand.get(j).add(card);
            }
        }

        // 打印展示出来
        System.out.println("第1个人牌：");
        System.out.println(hand1);
        System.out.println("第2个人牌：");
        System.out.println(hand2);
        System.out.println("第3个人牌：");
        System.out.println(hand3);
        System.out.println("剩下的牌：");
        System.out.println(cards);
    }

}