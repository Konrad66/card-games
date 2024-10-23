package konrad.poker.client;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import konrad.poker.client.actions.ActionManager;
import konrad.poker.server.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerGroup extends MyGroup {


    private HandGroup handGroup;
    private MoneyActor moneyActor;
    private Player player;
    private int cardsAdded = 0;

    public PlayerGroup(HandGroup handGroup, MoneyActor moneyActor, Player player, Mediator mediator) {
        super(mediator);
        if (moneyActor == null) {//null object pattern
            moneyActor = MoneyActor.getEmpty();
        }
        this.player = player;
        setupHandGroup(handGroup);
        setupMoneyActor(moneyActor);
        updateWidth();
    }

    private void setupHandGroup(HandGroup handGroup) {
        this.handGroup = handGroup;
        addActor(handGroup);
    }

    private void setupMoneyActor(MoneyActor moneyActor) {
        this.moneyActor = moneyActor;
        addActor(moneyActor);
    }

    //todo ułożenie kart na stole dac mozliwosc wylaczania licznika dla niektorych pozycji na potrzeby pozycji z prawej ukladanie kart w lewo;
    //blokowanie przesuniecia licznika lub nie
    //dokładanie kart w drugą stronę


    public void addCardWithAnimation(CardActor cardActor) {
        //todo anonimowa klasa
        MoveToAction moveCard = new MoveToAction() {
            @Override
            protected void begin() {
                cardActor.leaveGroup(); //startowa pozycja karty przed animacją
                super.begin(); //zaczytanie pozycji karty do animacji
                cardActor.setHidden(player.isHiddenCards());
                Vector2 target = getNewCardPosition();
                float targetX = target.x;
                float targetY = target.y;
                setPosition(targetX, targetY);
            }
        };

        //todo interfejs funkcyjny to taki z jedną metodą abstrakcyjną

        moveCard.setDuration(1);
        moveCard.setInterpolation(Interpolation.exp10);


        Action finishAnimation = new Action() { //todo anonimowe klasy
            @Override
            public boolean act(float delta) {
                cardActor.setX(0);
                cardActor.setY(0);
                handGroup.addActor(cardActor);
                updateWidth();
                return true;
            }
        };

        Action sequence = Actions.sequence(moveCard, finishAnimation);
        sequence.setTarget(cardActor);


        List<Action> finalAnimation = new ArrayList<>();
        finalAnimation.add(sequence);
        if (moneyActor.isPresent() && (cardsAdded == 0 || moneyActor.movable())) {
            Action moveMoney = Actions.moveBy(moneyActor.getMoveX(), 0, 0.7f, Interpolation.fastSlow);
            moveMoney.setTarget(moneyActor);
            finalAnimation.add(moveMoney);
        }
        ActionManager.getInstance().playActions(finalAnimation);
        cardsAdded++;
    }

    public void placeBidWithAnimation(int bid) {
        List<Action> actions = moneyActor.placeBidAnimated(bid);
        for (Action action : actions) {
            ActionManager.getInstance().playActions(List.of(action));
        }
    }

    private void updateWidth() {
        setWidth(handGroup.getWidth() + (moneyActor.isPresent() ? moneyActor.getWidth() : 0) + Dimensions.MARGIN);
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

    public Vector2 getMoneyVector() {
        return moneyActor.getStageVector();
    }

    public void increaseDealerMoney(int receivedMoney) {
        moneyActor.increaseMoney(receivedMoney);
    }

}

//todo przebudowa systemu animacji: wyraźnie oddzielenie kodu odbiorcy i adresata przy akcjach przekazywania
//ewentualna fabryka akcji
// inicjacja animacji:
//  wysyłający
//  odbierający
