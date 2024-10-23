package konrad.poker.client.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import java.util.ArrayList;
import java.util.List;

public class ActionSet {
    private List<Action> actions = new ArrayList<>();
    private ActionManager actionManager;

    public ActionSet(List<Action> parallelActions) {
        for (Action parallelAction : parallelActions) {
            ActionWrapper myAction = new ActionWrapper(parallelAction);
            myAction.informActionSetFinished(this);
            actions.add(myAction);
        }
    }

    public void play() {
        for (Action action : actions) {
            action.getTarget().addAction(action);
        }
    }

    public void actionFinished(Action action) {
        actions.remove(action);
        if (actionManager != null && actions.isEmpty()) {
            actionManager.actionSetFinished(this);
        }
    }

    void informActionManagerWhenFinished(ActionManager actionManager) {
        this.actionManager = actionManager;
    }
}
