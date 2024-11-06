package konrad.poker.client.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import konrad.poker.client.Mediator;

public class MyGroup extends Group {

    Mediator mediator;

    MyGroup(Mediator mediator) {
        this.mediator = mediator;
    }

    void leaveGroup() {
        Vector2 stageVector = getStageVector();
        setX(stageVector.x);
        setY(stageVector.y);
        mediator.spawnNewThing(this);
    }

    public Vector2 getStageVector() {
        return localToStageCoordinates(new Vector2(getX(),getY()));
    }
}