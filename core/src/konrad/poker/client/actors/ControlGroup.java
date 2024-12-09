package konrad.poker.client.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
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
        callButton.setBounds(0, 0, Dimensions.CARD_HEIGHT, BUTTON_HEIGHT);
        checkButton.setBounds(BUTTON_WIDTH + MARGIN, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        foldButton.setBounds((BUTTON_WIDTH * 2f) + MARGIN* 2, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        riseButton.setBounds((BUTTON_WIDTH * 3f) + MARGIN * 3, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        addActor(callButton);
        addActor(checkButton);
        addActor(foldButton);
        addActor(riseButton);
    }
}



//todo zrobić animacje dla grupy przycisków, żeby leciały od środka i "rozdawały się" podobnie jak karty