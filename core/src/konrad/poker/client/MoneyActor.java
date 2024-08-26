package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.Player;

public class MoneyActor extends Actor {

    private Texture texture;
    private Player player;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private BitmapFont font;
    private boolean movable;
    private Direction direction;


    public MoneyActor(Player player, BitmapFont font, boolean movable, Direction direction) {
        this.player = player;
        this.font = font;
        this.movable = movable;
        this.direction = direction;
        texture = new Texture(Gdx.files.internal("token/token.png"));
        setX(-Dimensions.MONEY_SIZE/8);
        setWidth(Dimensions.MONEY_SIZE);
        setHeight(Dimensions.MONEY_SIZE);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        font.setColor(Color.GREEN);
        font.draw(batch, player.getMoney() + "", getX() + getWidth() / 2 - 15, getHeight() / 2 + 5 + getY()); //todo dynamiczne wyliczanie
    }

    public boolean movable() {
        return movable;
    }

    public float getMoveX() {
        float move = Dimensions.CARD_WEIGHT + Dimensions.MARGIN;
        return direction == Direction.RIGHT ? move : -move;
    }
}
