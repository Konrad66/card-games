package konrad.poker.client;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.LinkedList;
import java.util.Queue;

public class DeckGroup extends Group {

    private Controller controller;
    private Queue<CardActor> cards = new LinkedList<>();
    private int drawn;

    //todo różnica między linkedLista a ArrayLista

    public DeckGroup(Controller controller) {
        this.controller = controller;
        addNextCard();
        addNextCard();
        setWidth(PokerGame.CARD_WEIGHT);
        setHeight(PokerGame.CARD_HEIGHT);
    }


    public void playerDraws(PlayerGroup player, int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard(player);
        }
    }

    private void drawCard(PlayerGroup player){
        CardActor card = cards.remove();
        card.leaveGroup();
        player.addCardWithAnimation(card);
        addNextCard();
    }


    private void addNextCard(){
        CardActor cardActor = new CardActor(controller.getDeckCard(drawn++));
        cards.add(cardActor);
        addActor(cardActor);
    }



}