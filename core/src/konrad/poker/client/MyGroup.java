package konrad.poker.client;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class MyGroup extends Group {

    Mediator mediator;

    MyGroup(Mediator mediator) {
        this.mediator = mediator;
    }

    void leaveGroup() {
        Vector2 stageVector = getStageVector();
        setX(stageVector.x);
        setY(stageVector.y);
        getStage().addActor(this);
    }

    private Vector2 getStageVector() {
        return localToStageCoordinates(new Vector2(getX(),getY()));
    }
}