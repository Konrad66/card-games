package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.Player;

public class MoneyActor extends MyActor {

    private Texture texture;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private BitmapFont font;
    private boolean movable;
    private Direction direction;
    private int money;
    private Mediator mediator;

    public MoneyActor(int money, BitmapFont font, boolean movable, Direction direction, Mediator mediator) {
        super(mediator);
        this.font = font;
        this.movable = movable;
        this.direction = direction;
        this.money = money;
        this.mediator = mediator;
        texture = new Texture(Gdx.files.internal("token/token.png"));
        setX(-Dimensions.MONEY_SIZE / 8);
        setWidth(Dimensions.MONEY_SIZE);
        setHeight(Dimensions.MONEY_SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        font.setColor(Color.GREEN);
        font.draw(batch, money + "", getX() + getWidth() / 2 - 15, getHeight() / 2 + 5 + getY()); //todo dynamiczne wyliczanie
    }

    public boolean movable() {
        return movable;
    }

    public float getMoveX() {
        float move = Dimensions.CARD_WEIGHT + Dimensions.MARGIN;
        return direction == Direction.RIGHT ? move : -move;
    }

    public void placeBid(int bidValue) {
        money -= bidValue;
        spawnNewBid(bidValue);
    }

    private void spawnNewBid(int bidValue) {
        MoneyActor bidActor = new MoneyActor(bidValue, font, false, Direction.LEFT, mediator);
        mediator.spawnNewThing(bidActor);

    }
}