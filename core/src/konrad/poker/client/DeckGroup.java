package konrad.poker.client;

import com.badlogic.gdx.scenes.scene2d.Group;

public class DeckGroup extends Group {

    private Controller controller;


    public DeckGroup(Controller controller) {
        this.controller = controller;
        addActor(new CardActor(controller.getDeckCard(0)));
        addActor(new CardActor(controller.getDeckCard(1)));
        setWidth(PokerGame.CARD_WEIGHT);
        setHeight(PokerGame.CARD_HEIGHT);
    }



}
