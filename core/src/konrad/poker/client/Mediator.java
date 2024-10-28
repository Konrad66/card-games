package konrad.poker.client;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

public interface Mediator {

    void spawnNewThing(Actor actor);

    Vector2 getDealerMoneyVector();

    void removeThing(Actor actor);

    void increaseDealerMoney(int money);
}