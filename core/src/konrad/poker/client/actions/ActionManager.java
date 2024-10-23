package konrad.poker.client.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.*;

public class ActionManager {
    //zastosowano wzorzec Singleton dla uzyskania globalnego dostępu do jednego obiektu AnimationManager
    private static ActionManager instance = new ActionManager();
    private Queue<ActionSet> sets = new LinkedList<>();

    //aby zablokować tworzenie obiektu tej klasy wystarczy ustawić konstruktor na prywatny
    private ActionManager() {}

    public static ActionManager getInstance() {
        return instance;
    }

    public void playActions(List<Action> parallelActions){
        ActionSet actionSet = new ActionSet(parallelActions);
        actionSet.informActionManagerWhenFinished(this);
        sets.add(actionSet);
        if (sets.size() == 1) {
           playFirstSet();
        }
    }

    private void playFirstSet() {
        sets.element().play();
    }

    public void actionSetFinished(ActionSet actionSet) {
        sets.remove(actionSet);
        if (!sets.isEmpty()) {
            playFirstSet();
        }
    }
}
