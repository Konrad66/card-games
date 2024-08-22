package konrad.poker.client;

import com.badlogic.gdx.scenes.scene2d.Group;
import konrad.poker.server.Card;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DeckGroup extends Group {

    private Controller controller;
    private Deque<CardActor> cards = new LinkedList<>();

    //todo różnica między linkedLista a ArrayLista

    public DeckGroup(Controller controller) {
        this.controller = controller;
        setWidth(PokerGame.CARD_WEIGHT);
        setHeight(PokerGame.CARD_HEIGHT);
        addAllCards();
    }

    private void addAllCards(){
        List<Card> deckCards = controller.getDeckCards();
        System.out.println(deckCards);
        for (int i = deckCards.size() - 1; i >= 0; i--) {
            CardActor cardActor = new CardActor(deckCards.get(i));
            cards.add(cardActor);
            addActor(cardActor);
        }
    }

    public void playerDraws(PlayerGroup player, int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard(player);
        }
    }

    private void drawCard(PlayerGroup player){
        CardActor card = cards.removeLast();
        player.addCardWithAnimation(card);

    }
}