package konrad.poker.client.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import konrad.poker.client.Dimensions;

public class MenuOptionActor extends Actor {

    private BitmapFont font;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private String name;

    public MenuOptionActor(BitmapFont font, String name) {
        this.font = font;
        this.name = name;
        setBounds(Dimensions.CENTER_X, Dimensions.CENTER_Y, Dimensions.CARD_HEIGHT, Dimensions.MARGIN);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Kliknięty " + name);
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        shapeRenderer.end();
        //TODO wykadrować napis, dodać drugi przycisk
        batch.begin();
        font.draw(batch, name, getX(), getY());
    }
}