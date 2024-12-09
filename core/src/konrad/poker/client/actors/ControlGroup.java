package konrad.poker.client.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import konrad.poker.client.Dimensions;

public class ControlGroup extends Group {

    private static final int BUTTON_HEIGHT = Dimensions.MARGIN * 3;
    private static final int MARGIN = Dimensions.MARGIN;
    private static final int BUTTON_WIDTH = Dimensions.CARD_HEIGHT;

    private ButtonActor callButton = new ButtonActor("Call", "pokerHoldem.png");
    private ButtonActor checkButton = new ButtonActor("Check", "blackjack.png");
    private ButtonActor foldButton = new ButtonActor("Fold", "classicPoker.png");
    private ButtonActor riseButton = new ButtonActor("Rise", "blackjack.png");

    public ControlGroup() {
        setButtons();
        addActions();
    }

    private void setButtons() {
        callButton.setBounds(0, 0, Dimensions.CARD_HEIGHT, BUTTON_HEIGHT);
        checkButton.setBounds(BUTTON_WIDTH + MARGIN, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        foldButton.setBounds((BUTTON_WIDTH * 2f) + MARGIN* 2, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        riseButton.setBounds((BUTTON_WIDTH * 3f) + MARGIN * 3, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        addActor(callButton);
        addActor(checkButton);
        addActor(foldButton);
        addActor(riseButton);
    }


    public void addActions(){
        callButton.addAction(prepareAction(() -> System.out.println("call")));
        checkButton.addAction(prepareAction(() -> System.out.println("check")));
    }


    //todo powtórka z MenuScreena
    private InputListener prepareAction(Runnable action) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                action.run();
                return true;
            }
        };
    }
}


//todo ustawiać pozycje pętlą, dodać je do zbioru i dzięki pętli ustawiać ich pozycję
//todo zrobić animacje dla grupy przycisków, żeby leciały od środka i "rozdawały się" podobnie jak karty