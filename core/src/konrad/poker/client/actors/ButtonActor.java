package konrad.poker.client.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.*;

public class ButtonActor extends Actor {

    private String name;
    private Texture texture;

    public ButtonActor(String name, String imagePath) {
        this.name = name;
        this.texture = new Texture(Gdx.files.internal(imagePath));
    }

    public void addAction(InputListener action) {
        addListener(action);
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