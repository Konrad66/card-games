package konrad.poker.client.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import konrad.poker.client.CardGame;
import konrad.poker.client.Dimensions;

public class MenuOptionActor extends Actor {

    private BitmapFont font;
    private String name;
    private Texture texture;
    private CardGame cardGame;


    public MenuOptionActor(BitmapFont font, String name, String imagePath, CardGame cardGame) {
        this.font = font;
        this.name = name;
        this.texture = new Texture(Gdx.files.internal(imagePath));
        this.cardGame = cardGame;
        setBounds(100, 500, Dimensions.WINDOW_WIDTH / 4f, Dimensions.WINDOW_HEIGHT / 4f);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(getName().equals("Poker")){
                    cardGame.changeScreenToPoker();
                } else if (getName().equals("BlackJack")) {
                    cardGame.changeScreenToBlackJack();
                }
                return true;
            }
        });
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.end();
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.GREEN);
//        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
//        shapeRenderer.end();
//        //TODO wykadrować napis, dodać drugi przycisk
//        batch.begin();
//        font.draw(batch, name, getX(), getY());
    }
}