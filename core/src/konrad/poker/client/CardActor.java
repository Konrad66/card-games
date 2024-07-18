package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.Card;

public class CardActor extends Actor {

    private Card card;
    private Texture texture;
    private static final String CARDS_PATH = "cards/";

    public CardActor(Card card) {
        this.card = card;
        String path = filesPath(card);
        texture = new Texture(Gdx.files.internal(path));
        setBounds(0, 0, 100, 100);
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


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}