package konrad.poker.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import konrad.poker.server.BlackJackGameRules;
import konrad.poker.server.GameService;
import konrad.poker.server.ClassicPokerGameRules;
import konrad.poker.server.PokerHoldemGameRules;

public class CardGame extends Game {

    private BitmapFont font;
    private SpriteBatch batch;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;


    @Override
    public void create() {
        font = new BitmapFont();
        batch = new SpriteBatch();
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
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

    public void changeScreenToPokerHoldem() {
        gameScreen = new GameScreen(this, new GameService(new PokerHoldemGameRules()));
        setScreen(gameScreen);
    }

    public void changeScreenToBlackJack() {
        gameScreen = new GameScreen(this, new GameService(new BlackJackGameRules()));
        setScreen(gameScreen);
    }

    public void changeScreenToClassicPoker(){
        gameScreen = new GameScreen(this, new GameService(new ClassicPokerGameRules()));
        setScreen(gameScreen);
    }
}