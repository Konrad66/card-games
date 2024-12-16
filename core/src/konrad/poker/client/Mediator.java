package konrad.poker.client;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.Command;
import konrad.poker.server.CommandType;

public interface Mediator {

    void spawnNewThing(Actor actor);

    Vector2 getDealerMoneyVector();

    void removeThing(Actor actor);

    void increaseDealerMoney(int money);

    default void executeHumanCommand(CommandType commandType){
        executeHumanCommand(commandType, -1);
    }

    void executeHumanCommand(CommandType commandType, int amount);
}