package konrad.poker.client;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.List;



public class HandGroup extends Group {


    private NewCardDirection direction;

    public HandGroup(NewCardDirection direction) {
        this.direction = direction;
    }

    @Override
    public void addActor(Actor actor) {
        actor.setX(getNewCardX());
        if (direction == NewCardDirection.RIGHT) {
            setWidth(getWidth() + actor.getWidth());
        }
        super.addActor(actor);
    }

    public void addActors(List<CardActor> cardActors) {
        for (CardActor cardActor : cardActors) {
            addActor(cardActor);
        }
    }

    Vector2 getNewCardPosition() {
        return getStageVector().add(getNewCardX(), 0);
    }

    private float getNewCardX() {
        float x = PokerGame.CARD_WEIGHT * getChildren().size;
        return direction == NewCardDirection.RIGHT ? x : -x;
    }


    private Vector2 getStageVector() {
        return localToStageCoordinates(new Vector2(getX(),getY()));
    }

    public NewCardDirection getDirection() {
        return direction;
    }
}
