package konrad.poker.client;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class HandGroup extends Group {

    @Override
    public void addActor(Actor actor) {
        actor.setX(PokerGame.CARD_WEIGHT * getChildren().size);
        setWidth(getWidth() + actor.getWidth());
        super.addActor(actor);
    }
}
