package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.Card;

public class CardActor extends MyActor {

    private static final String CARDS_PATH = "cards/";
    private static final String REVERS_PATH = CARDS_PATH + "card_back/cardBackBlue.png";

    private Texture aversTexture;
    private Texture reversTexture;
    private boolean hidden = true;

    public CardActor(Card card, Mediator mediator) {
        super(mediator);
        String path = filesPath(card);
        aversTexture = new Texture(Gdx.files.internal(path));
        reversTexture = new Texture(Gdx.files.internal(REVERS_PATH));
        setBounds(0, 0, Dimensions.CARD_WEIGHT, Dimensions.CARD_HEIGHT);
    }

    private String filesPath(Card card) {
        StringBuilder controlPath = new StringBuilder(CARDS_PATH);
        controlPath.append(card.getColor().getName())
                .append("/card")
                .append(Character.toUpperCase(card.getColor().getName().charAt(0)))
                .append(card.getColor().getName().substring(1))
                .append("_")
                .append(card.getRank().getSymbol())
                .append(".png");
        return controlPath.toString();
    }

    //concatenacja - używanie + do łączenia stringów

//417 x 654
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Texture texture = aversTexture;
        if (hidden) {
            texture = reversTexture;
        }
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }



    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    // 2
}