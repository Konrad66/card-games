package konrad.poker.client;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.List;



public class HandGroup extends Group {


    private Direction direction;

    public HandGroup(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void addActor(Actor actor) {
        actor.setX(getNewCardX());
        if (direction == Direction.RIGHT) {
            setWidth(getWidth() + actor.getWidth() + Dimensions.MARGIN);
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
        float x = Dimensions.CARD_WEIGHT  * getChildren().size + Dimensions.MARGIN/2f * getChildren().size;
        return direction == Direction.RIGHT ? x : -x;
    }


    private Vector2 getStageVector() {
        return localToStageCoordinates(new Vector2(getX(),getY()));
    }

    public Direction getDirection() {
        return direction;
    }
}
