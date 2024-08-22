package konrad.poker.client;

import static konrad.poker.client.PokerGame.*;

public class PokerGameLayout {


    PlayerLayout getLayoutFor(int position){
        PlayerLayout playerLayout = new PlayerLayout();
        playerLayout.withCounter = true;
        switch (position){
            case 1:
                playerLayout.x= WINDOW_SIZE / 2 - CARD_WEIGHT / 2;
                playerLayout.y = MARGIN;
                playerLayout.movable = true;
                playerLayout.handDirection = Direction.RIGHT;
                break;
            case 2:
                playerLayout.x = MARGIN;
                playerLayout.y = WINDOW_SIZE / 2;
                playerLayout.movable = false;
                playerLayout.handDirection = Direction.RIGHT;
                break;
        }



        if (position <= 0 || position > 5) {
            throw new IllegalArgumentException("Valid positions: 1-5");
        }
        return playerLayout;
    }

    class PlayerLayout{
        private int x;
        private int y;
        private boolean movable;
        private Direction handDirection;
        private Direction moneyDirection;

        private boolean withCounter;

        public PlayerLayout() {

        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isMovable() {
            return movable;
        }

        public Direction getHandDirection() {
            return handDirection;
        }

        public boolean isWithMoney() {
            return withCounter;
        }

        public Direction getMoneyDirection() {
            return moneyDirection;
        }
    }

}
