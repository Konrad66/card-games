package konrad.poker.client;

import static konrad.poker.client.PokerGame.*;

public class PokerGameLayout {

    PlayerLayout getLayoutFor(int position) {
        PlayerLayout playerLayout = new PlayerLayout();
        playerLayout.withMoney = true;
        switch (position) {
            case 1:
                playerLayout.x = WINDOW_WIDTH / 3;
                playerLayout.y = MARGIN;
                playerLayout.movable = true;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.RIGHT;
                break;
            case 2:
                playerLayout.x = MARGIN * 2 + CARD_WEIGHT;
                playerLayout.y = WINDOW_HEIGHT / 3;
                playerLayout.movable = false;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.LEFT;
                break;
            case 3:
                playerLayout.x = MARGIN * 2 + CARD_WEIGHT;
                playerLayout.y = WINDOW_HEIGHT / 2;
                playerLayout.movable = false;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.LEFT;
                break;
            case 4:
                playerLayout.x = WINDOW_WIDTH - 300;
                playerLayout.y = WINDOW_HEIGHT / 2;
                playerLayout.movable = true;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.RIGHT;
                break;
            case 5:
                playerLayout.x = WINDOW_WIDTH - 300;
                playerLayout.y = WINDOW_HEIGHT / 3;
                playerLayout.movable = true;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.RIGHT;
                break;
        }

        if (position <= 0 || position > 5) {
            throw new IllegalArgumentException("Valid positions: 1-5");
        }
        return playerLayout;
    }

    class PlayerLayout {
        private int x;
        private int y;
        private boolean movable;
        private Direction handDirection;
        private Direction moneyDirection;
        private boolean withMoney;

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
            return withMoney;
        }

        public Direction getMoneyDirection() {
            return moneyDirection;
        }
    }
}