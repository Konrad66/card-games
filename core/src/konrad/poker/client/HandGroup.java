package konrad.poker.client;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.List;

public class HandGroup extends Group {

    @Override
    public void addActor(Actor actor) {
        actor.setX(PokerGame.CARD_WEIGHT * getChildren().size);
        setWidth(getWidth() + actor.getWidth());
        super.addActor(actor);
    }

    public void addActors(List<CardActor> cardActors) {
        for (CardActor cardActor : cardActors) {
            addActor(cardActor);
        }
    }

    Vector2 getNewCardPosition() {
        return getStageVector().add(getWidth(), 0);
    }

    private Vector2 getStageVector() {
        return localToStageCoordinates(new Vector2(getX(),getY()));
    }
}
