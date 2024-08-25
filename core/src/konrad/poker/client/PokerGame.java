package konrad.poker.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PokerGame extends Game {

    public static final int WINDOW_WIDTH =1280;
    public static final int MARGIN = 20;

    public static final int CARD_HEIGHT = 654/5;
    public static final int CARD_WEIGHT = 417/5;
    public static final int WINDOW_HEIGHT = 720;

    private BitmapFont font;
    private SpriteBatch batch;


    @Override
    public void create() {
        font = new BitmapFont();
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }
}
