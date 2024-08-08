package konrad.poker.client;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.List;

public class PlayerGroup extends Group {

   private HandGroup handGroup;
   private MoneyActor moneyActor;

    public PlayerGroup(HandGroup handGroup, MoneyActor moneyActor) {
        this.handGroup = handGroup;
        this.moneyActor = moneyActor;
        addActor(handGroup);
        addActor(moneyActor);
        moneyActor.setX(handGroup.getWidth() + PokerGame.MARGIN);
        updateWidth();
        moneyActor.setY(PokerGame.MARGIN);
    }




    public void addCardWithAnimation(CardActor cardActor) {
        Vector2 target = getNewCardPosition();
        float targetX = target.x;
        float targetY = target.y;

        Action moveCard = Actions.moveTo(targetX, targetY, 1, Interpolation.fastSlow);
        Action moveMoney = Actions.moveBy(cardActor.getWidth(),0,0.7f,Interpolation.fastSlow);
        moveMoney.setTarget(moneyActor);

        Action finshAnimation = new Action() { //todo anonimowe klasy
            @Override
            public boolean act(float delta) {
                cardActor.setX(0);
                cardActor.setY(0);
                handGroup.addActor(cardActor);
                updateWidth();
                return true;
            }
        };

        Action sequence = Actions.sequence( moveCard, finshAnimation);
        sequence.setTarget(cardActor);
        ActionManager.getInstance().playActions(List.of(sequence,moveMoney));
    }

    private void updateWidth() {
        setWidth(handGroup.getWidth() + moneyActor.getWidth() + PokerGame.MARGIN);
    }

    Vector2 getNewCardPosition() {
        return handGroup.getNewCardPosition();
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof CardActor) {
            throw new IllegalArgumentException("Don't add card directly to PlayerGroup. Add to HandGroup");
        }
        super.addActor(actor);
    }

}
