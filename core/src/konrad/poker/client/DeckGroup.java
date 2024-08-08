package konrad.poker.client;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;

public class DeckGroup extends Group {

    private Controller controller;
    private CardActor firstCard;
    private CardActor secondCard;

    public DeckGroup(Controller controller) {
        this.controller = controller;
        firstCard = new CardActor(controller.getDeckCard(0));
        secondCard = new CardActor(controller.getDeckCard(1));

        addActor(firstCard);
        addActor(secondCard);
        setWidth(PokerGame.CARD_WEIGHT);
        setHeight(PokerGame.CARD_HEIGHT);

        
    }


    public void playerDraws(PlayerGroup player, int amount) {
        firstCard.leaveGroup();
        player.addCardWithAnimation(firstCard);
        secondCard.leaveGroup();
        player.addCardWithAnimation(secondCard);
        //todo poczytac u Ciebei jak to w kodzie wyglada (manager animacji)
    }
}