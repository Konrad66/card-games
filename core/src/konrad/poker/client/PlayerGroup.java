package konrad.poker.client;

import com.badlogic.gdx.scenes.scene2d.Group;
import konrad.poker.server.Card;

import java.util.List;

public class PlayerGroup extends Group {

   private HandGroup handGroup;
   private MoneyActor moneyActor;

    public PlayerGroup(HandGroup handGroup, MoneyActor moneyActor) {
        this.handGroup = handGroup;
        this.moneyActor = moneyActor;
        addActor(handGroup);
        addActor(moneyActor);
        moneyActor.setX(handGroup.getWidth());
        setWidth(handGroup.getWidth() + moneyActor.getWidth());
    }

    void drawCards(int count){
        System.out.println("Gracz dobiera " + count + "kart");
    }


}
