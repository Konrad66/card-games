package konrad.poker.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.List;

public class MoneyActor extends MyActor {

    private Texture texture;
    private GlyphLayout glyphLayout = new GlyphLayout();
    private BitmapFont font;
    private boolean movable;
    private Direction direction;
    private int money;
    private Mediator mediator;
    private float yOffset = Dimensions.MARGIN *0.75f * 0;

    public MoneyActor(int money, BitmapFont font, boolean movable, Direction direction, Mediator mediator) {
        super(mediator);
        this.font = font;
        this.movable = movable;
        this.direction = direction;
        this.money = money;
        this.mediator = mediator;
        texture = new Texture(Gdx.files.internal("token/token.png"));
        setX(-Dimensions.MONEY_SIZE / 8);
        setY(yOffset);
        setWidth(Dimensions.MONEY_SIZE);
        setHeight(Dimensions.MONEY_SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
        batch.setColor(Color.WHITE);
        //batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        font.setColor(Color.GREEN);
        font.draw(batch, money + "", getX() + getWidth() / 2 - 15, getHeight() / 2 + 5 + getY()); //todo dynamiczne wyliczanie
        super.draw(batch, parentAlpha);
    }

    public boolean movable() {
        return movable;
    }

    public float getMoveX() {
        float move = Dimensions.CARD_WEIGHT + Dimensions.MARGIN;
        return direction == Direction.RIGHT ? move : -move;
    }

    public List<Action> placeBidAnimated(int bidValue) {
        Vector2 dealerVector = mediator.getDealerMoneyVector();
        MoveToAction moveBid = new MoveToAction() {
            @Override
            protected void begin() {
                super.begin();
                setPosition(dealerVector.x, dealerVector.y);
                setDuration(1);
                setInterpolation(Interpolation.exp10);
            }
        };
        AlphaAction fadeBid = Actions.fadeOut(0.3f);
        Action removeBid = new Action() {
            @Override
            public boolean act(float delta) {
                mediator.removeThing(target);
                return true;
            }
        };
        SequenceAction sequence = Actions.sequence(moveBid, fadeBid, removeBid);
        Action spawnBid = new Action() {
            @Override
            public boolean act(float v) {
                MoneyActor bidActor = spawnNewBid(bidValue);
                sequence.setTarget(bidActor);
//                moveBid.setTarget(bidActor);
//                fadeBid.setTarget(bidActor);
//                removeBid.setTarget(bidActor);
                return true;
            }
        };
        spawnBid.setTarget(this);
        return List.of(spawnBid, sequence);
    }

    private MoneyActor spawnNewBid(int bidValue) {
        money -= bidValue;
        MoneyActor bidActor = new MoneyActor(bidValue, font, false, Direction.LEFT, mediator);
        Vector2 stageVector = getStageVector();
        bidActor.setX(0);
        bidActor.setY(0);
        bidActor.setX(stageVector.x);
        bidActor.setY(stageVector.y - yOffset);
        mediator.spawnNewThing(bidActor);
       return bidActor;
    }
}