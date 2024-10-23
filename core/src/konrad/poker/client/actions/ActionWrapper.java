package konrad.poker.client.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;

//wzorzec proxy
public class ActionWrapper extends Action {
    private Action action;
    private ActionSet actionSet;

    public ActionWrapper(Action action) {
        this.action = action;
    }

    @Override
    public boolean act(float delta) {
        boolean finished = action.act(delta);
        if (actionSet != null && finished) {
            actionSet.actionFinished(this);
        }
        return finished;
    }

    void informActionSetFinished(ActionSet actionSet) {
        this.actionSet = actionSet;
    }

    @Override
    public Actor getTarget() {
        return action.getTarget();
    }

    @Override
    public void restart() {
        action.restart();
    }

    @Override
    public void setActor(Actor actor) {
        action.setActor(actor);
    }

    @Override
    public Actor getActor() {
        return action.getActor();
    }

    @Override
    public void setTarget(Actor target) {
        action.setTarget(target);
    }

    @Override
    public void reset() {
        action.reset();
    }

    @Override
    public Pool getPool() {
        return action.getPool();
    }

    @Override
    public void setPool(Pool pool) {
        action.setPool(pool);
    }

    @Override
    public String toString() {
        return action.toString();
    }
}
