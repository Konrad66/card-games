package konrad.poker.client;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static konrad.poker.client.PokerGame.WINDOW_SIZE;

public class GameScreen implements Screen {


    private Controller controller;
    private OrthographicCamera camera;
    private PokerGame pokerGame;
    private Stage stage;


    public GameScreen(PokerGame pokerGame) {
        this.pokerGame = pokerGame;
        controller = new Controller(pokerGame);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WINDOW_SIZE,WINDOW_SIZE);
        stage = new Stage(new ScreenViewport(),pokerGame.getBatch());

        PlayerGroup player = controller.createPlayer();
        stage.addActor(player);
        player.setX(WINDOW_SIZE/2f - player.getWidth()/2);
    }

    /*
    * Pokazywanie ilosci kasy na liczniku
    * pokazywanie ręki z 2 kart
    * Polaczenie reki i licznika jako jeden element graficzny Gracza
    * Dodanie stosu kart do dobierania
    * Animacja dobrania kart
    * */

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) { //delta = czas który minął od poprzedniej klatki
        ScreenUtils.clear(Color.WHITE);
        camera.update();
        pokerGame.getBatch().setProjectionMatrix(camera.combined);
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
