package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.Player;
import org.w3c.dom.Text;

public class MoneyActor extends Actor {

    private Texture texture;
    private Player player;

    public MoneyActor( Player player) {
             this.player = player;
             texture = new Texture( Gdx.files.internal("token/token.png"));
             setWidth(100);
             setHeight(100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
