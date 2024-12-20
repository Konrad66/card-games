package konrad.poker.client.actors;

import konrad.poker.client.*;
import konrad.poker.server.Card;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class DeckGroup extends MyGroup {

    private Controller controller;
    private Deque<CardActor> cards = new LinkedList<>();

    //todo różnica między linkedLista a ArrayLista

    public DeckGroup(Controller controller, Mediator mediator) {
        super(mediator);
        this.controller = controller;
        setWidth(Dimensions.CARD_WEIGHT);
        setHeight(Dimensions.CARD_HEIGHT);
        addAllCards();
    }

    private void addAllCards() {
        List<Card> deckCards = controller.getDeckCards();
        for (int i = deckCards.size() - 1; i >= 0; i--) {
            CardActor cardActor = new CardActor(deckCards.get(i), mediator);
            cards.add(cardActor);
            addActor(cardActor);
        }

        //todo UnaryOperator
        //unary - czyli że działa na jednym parametrze
//        IntStream.iterate(51, i -> i - 1)
//                .limit(52)
//                .forEach(i -> {
//                    CardActor cardActor = new CardActor(deckCards.get(i));
//                    cards.add(cardActor);
//                    addActor(cardActor);
//                });
    }

    public void playerDraws(PlayerGroup player, int amount) {
        //todo przeanalizowac
//        for (int i = 0; i < amount; i++) {
//            drawCard(player);
//        }
        IntStream.range(0, amount)
                .forEach(i -> drawCard(player));
    }

    private void drawCard(PlayerGroup player) {
        CardActor card = cards.removeLast();
        player.addCardWithAnimation(card);
    }

    //todo prośba jeszcze raz wytłumaczyć this
}