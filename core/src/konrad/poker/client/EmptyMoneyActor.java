package konrad.poker.client;

import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.ArrayList;
import java.util.List;

public class EmptyMoneyActor extends MoneyActor{

    private static final EmptyMoneyActor instance = new EmptyMoneyActor();

    private EmptyMoneyActor() {
        super(0,null,false,null,null);
    }

    public static EmptyMoneyActor getInstance(){
        return instance;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {}

    @Override
    public boolean movable() {
        return false;
    }

    @Override
    public float getMoveX() {
        return 0;
    }

    @Override
    public List<Action> placeBidAnimated(int bidValue) {
        return new ArrayList<>();
    }

    @Override
    public void increaseMoney(int receivedMoney) {}

    @Override
    public boolean isPresent() {
        return false;
    }
}
