package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.Player;
import org.w3c.dom.Text;

public class MoneyActor extends Actor {

    private Texture texture;
    private Player player;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private BitmapFont font;

    public MoneyActor(Player player, BitmapFont font) {
             this.player = player;
             this.font = font;
             texture = new Texture( Gdx.files.internal("token/token.png"));
             setWidth(100);
             setHeight(100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        font.setColor(Color.GREEN);
        font.draw(batch,player.getMoney() + "",getX() + getWidth()/2 - 15,getHeight()/2 + 5); //todo dynamiczne wyliczanie
    }
}
