package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import konrad.poker.client.actors.ButtonActor;

public class MenuScreen implements Screen {

    private OrthographicCamera camera;
    private Stage stage;
    private CardGame cardGame;


    public MenuScreen(CardGame cardGame) {
        this.cardGame = cardGame;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        stage = new Stage(new ScreenViewport(), cardGame.getBatch());
        Gdx.input.setInputProcessor(stage);


        //todo nie powtarzać tego samego 3 razy
        ButtonActor pokerHoldem = new ButtonActor("Poker", "pokerHoldem.png");
        pokerHoldem.setBounds(100, 500, Dimensions.WINDOW_WIDTH / 4f, Dimensions.WINDOW_HEIGHT / 4f);
        pokerHoldem.addAction(prepareAction(() -> cardGame.changeScreenToPokerHoldem()));
        ButtonActor blackJack = new ButtonActor("BlackJack", "blackjack.png");
        blackJack.setBounds(100, 500, Dimensions.WINDOW_WIDTH / 4f, Dimensions.WINDOW_HEIGHT / 4f);
        blackJack.addAction(prepareAction(() -> cardGame.changeScreenToBlackJack()));
        blackJack.setY(Dimensions.CARD_WEIGHT);
        ButtonActor classicPoker = new ButtonActor("ClassicPoker", "classicPoker.png");
        classicPoker.setBounds(100, 500, Dimensions.WINDOW_WIDTH / 4f, Dimensions.WINDOW_HEIGHT / 4f);
        classicPoker.addAction(prepareAction(() -> cardGame.changeScreenToClassicPoker()));
        classicPoker.setX(Dimensions.WINDOW_WIDTH - Dimensions.CARD_WEIGHT * 7f - Dimensions.MARGIN);
//        classicPoker.setX(Dimensions.WINDOW_WIDTH / 3.75f);
        classicPoker.setY(Dimensions.WINDOW_HEIGHT - Dimensions.CARD_HEIGHT * 2f - Dimensions.MARGIN);
//        classicPoker.setY(Dimensions.WINDOW_HEIGHT / 2f);
        stage.addActor(pokerHoldem);
        stage.addActor(blackJack);
        stage.addActor(classicPoker);

        //Consumer, Supplier,

        //todo działa menu screen na game screenie
    }


    private InputListener prepareAction(Runnable action) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                action.run();
                return true;
            }
        };
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);

        camera.update();
        cardGame.getBatch().setProjectionMatrix(camera.combined);
        stage.act(delta); //aktorzy wykonują swoje akcje
        stage.draw(); //renderujemy aktora dla danej klatki
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
