import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Hand {
    private List<String> cards = new ArrayList<>();
    private Map<String, Integer> values = new HashMap<String, Integer>();
    private Map<String, Integer> handValues = new HashMap<String, Integer>();
    private String handType;

    public String getHandType() {
        return handType;
    }

    public List<String> getCards() {
        return cards;
    }

    public Hand(String card1, String card2, String card3, String card4, String card5) {
        this.cards.add(card1);
        this.cards.add(card2);
        this.cards.add(card3);
        this.cards.add(card4);
        this.cards.add(card5);
        this.values.put("A", 14);
        this.values.put("K", 13);
        this.values.put("Q", 12);
        this.values.put("J", 11);
        this.values.put("T", 10);
        this.handValues.put("High Card", 1);
        this.handValues.put("Pair", 2);
        this.handValues.put("Two Pair", 3);
        classifyHand();
    }

    public void classifyHand() {
        Map<String, Integer> pair = new HashMap<>();
        getSameCardMap(pair, cards);
        if (pair.values().stream().filter(s -> s.equals(2)).count() == 1) {
            this.handType = "Pair";
        } else if (pair.values().stream().filter(s -> s.equals(2)).count() == 2) {
            this.handType = "Two Pair";
        } else {
            this.handType = "High Card";
        }
    }

    private void getSameCardMap(Map<String, Integer> pair, List<String> cards) {
        List<String> tempList = new ArrayList<>();
        pair.clear();
        cards.stream().forEach(value -> tempList.add(value.substring(0, 1)));
        tempList.stream().forEach(getSameCardConsumer(pair));
    }


    public String compareHand(Hand hand) {
        int cardA = 0;
        int cardB = 0;
        if (getHandValue(hand.(getHandType)) == getHandValue(handType)) {
            if (handType.equals("High Card")) {
                cardA = getCardValue(getHighCard(cards).substring(0, 1));
                cardB = getCardValue(getHighCard(hand.getCards()).substring(0, 1));
            } else if (handType.equals("Pair")) {
                Map<String, Integer> pair = new HashMap<>();
                getSameCardMap(pair, cards);
                cardA = getCardValue(
                        pair.entrySet().stream().filter(p -> p.getValue() == 2).toString()
                );
                getSameCardMap(pair, hand.getCards());
                cardB = getCardValue(pair.entrySet().stream().filter(p -> p.getValue() == 2).findFirst().get().getKey());

            } else if (handType.equals("Two Pair")) {

            }
        } else if (getHandValue(hand.getHandType()) < getHandValue(handType)) {
            return "Win";
        }

        if (cardA > cardB) {
            return "Win";
        }

        return "Lose";
    }

    private Consumer<String> getSameCardConsumer(Map<String, Integer> pair) {
        return t -> {
            Integer i = pair.get(t);
            if (i == null) {
                i = 0;
            }
            pair.put(t, i + 1);
        };
    }

    public String getHighCard(List<String> cards) {
        String result = "";
        for (String card : cards) {
            if (result.equals("")) {
                result = card;
            } else {

                int cardA = getCardValue(result.substring(0, 1));
                int cardB = getCardValue(card.substring(0, 1));

                if (cardA < cardB) {
                    result = card;
                }
            }
        }
        return result;
    }


    public int getCardValue(String cards) {
        int result;
        result = values.get(cards) != null ? values.get(cards) : Integer.parseInt(cards);
        return result;
    }

    public int getHandValue(String hand) {
        int result;
        result = handValues.get(hand) != null ? handValues.get(hand) : Integer.parseInt(hand);
        return result;
    }
}
