package konrad.poker.client;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static konrad.poker.client.PokerGame.*;

public class GameScreen implements Screen {


    private Controller controller;
    private OrthographicCamera camera;
    private PokerGame pokerGame;
    private Stage stage;


    public GameScreen(PokerGame pokerGame) {
        this.pokerGame = pokerGame;
        //zbudowanie okna i backendu
        controller = new Controller(pokerGame);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WINDOW_SIZE,WINDOW_SIZE);
        stage = new Stage(new ScreenViewport(),pokerGame.getBatch());

        controller.setupActors(); //tworzymy elementy gry

        PlayerGroup player = controller.getPlayer();
        DeckGroup deckGroup = controller.getDeck();

        stage.addActor(deckGroup);
        stage.addActor(player);
        player.setX(WINDOW_SIZE/2f - player.getWidth()/2);
        player.setY(MARGIN);

        System.out.println(WINDOW_SIZE);
        deckGroup.setX(WINDOW_SIZE - CARD_WEIGHT - MARGIN);
        deckGroup.setY(WINDOW_SIZE - CARD_HEIGHT - MARGIN);

        controller.startGame(); //rozpoczecie gry (rozdawanie itd


        //rozgrywka

    }

    /*
    * Pokazywanie ilosci kasy na liczniku
    * pokazywanie ręki z 2 kart
    * Polaczenie reki i licznika jako jeden element graficzny Gracza
    * Zakryte karty
    * Dodanie stosu kart do dobierania
    * Pobranie z backendu wybranej karty ze stosu
    *
    * Animacja dobrania kart
    * Przekazanie graczowi na rękę dobranych kart
    * Wyświetlenie pozostałych graczy
    * Rotacje?
    * Rozdanie wszystkim graczom
    * 3 karty na stół
    * */

    //

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
