package konrad.poker.client;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import konrad.poker.client.actors.DeckGroup;
import konrad.poker.client.actors.PlayerGroup;
import konrad.poker.server.GameService;

import java.util.Map;

public class GameScreen implements Screen {


    private Controller controller;
    private OrthographicCamera camera;
    private CardGame cardGame;
    private Stage stage;


    public GameScreen(CardGame cardGame, GameService gameService) {
        this.cardGame = cardGame;
        //zbudowanie okna i backendu
        controller = new Controller(cardGame, this, gameService);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        stage = new Stage(new ScreenViewport(), cardGame.getBatch());


        controller.setupActors(); //tworzymy elementy gry

        //todo nieintuicyjne
        Map<Integer, PlayerGroup> players = controller.getPlayers();
        for (PlayerGroup player : players.values()) {
            stage.addActor(player);
            //todo zaktualizować
        }
        DeckGroup deckGroup = controller.getDeck();
        stage.addActor(deckGroup);

        //todo ustawić centrum
        deckGroup.setX(Dimensions.CENTER_X - Dimensions.CARD_WEIGHT/2f);
        //deckGroup.setX(Dimensions.CENTER_X - Dimensions.CARD_WEIGHT/2f);
        //deckGroup.setY(Dimensions.CENTER_Y - Dimensions.CARD_HEIGHT/2f);
        deckGroup.setY(Dimensions.WINDOW_HEIGHT - Dimensions.CARD_HEIGHT - Dimensions.MARGIN);

        controller.startGame(); //rozpoczecie gry (rozdawanie itd)


        //rozgrywka

    }

    public void addToStage(Actor actor){
        stage.addActor(actor);
    }

    public void removeFromStage(Actor actor){
        stage.getRoot().removeActor(actor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) { //delta = czas który minął od poprzedniej klatki
        ScreenUtils.clear(Color.DARK_GRAY);

        camera.update();
        cardGame.getBatch().setProjectionMatrix(camera.combined);
        stage.act(delta); //aktorzy wykonują swoje akcje
        stage.draw(); //renderujemy aktora dla danej klatki
        //todo debug
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
