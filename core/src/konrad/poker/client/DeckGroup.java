package konrad.poker.client;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class DeckGroup extends Group {

    private Controller controller;
    private CardActor firstCard;
    private CardActor secondCard;

    public DeckGroup(Controller controller) {
        this.controller = controller;
        firstCard = new CardActor(controller.getDeckCard(0));
        secondCard = new CardActor(controller.getDeckCard(1));
        addActor(firstCard);
      //  addActor(secondCard);
        setWidth(PokerGame.CARD_WEIGHT);
        setHeight(PokerGame.CARD_HEIGHT);
    }


    public void playerDraws(PlayerGroup player, int amount) {
        getParent().addActor(firstCard);
        firstCard.setX(getX());
        firstCard.setY(getY());

        float targetX = player.getX();
        float targetY = player.getY();


        Action move = Actions.moveTo(targetX, targetY, 1);

        Action addToGroup = new Action() { //todo anonimowe klasy
            @Override
            public boolean act(float delta) {
                firstCard.setX(0);
                firstCard.setY(0);
                player.addActor(firstCard);
                return true;
            }
        };

        Action sequence = Actions.sequence(move, addToGroup);

        firstCard.addAction(sequence);



    }
}
