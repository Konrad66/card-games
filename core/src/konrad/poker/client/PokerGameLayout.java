package konrad.poker.client;

import konrad.poker.client.actors.Direction;

public class PokerGameLayout {

    PlayerLayout getLayoutFor(int position) {
        PlayerLayout playerLayout = new PlayerLayout();
        playerLayout.withMoney = true;
        int leftMargin = Dimensions.MARGIN + Dimensions.MONEY_SIZE;
        int rightMargin = Dimensions.WINDOW_WIDTH - leftMargin - Dimensions.MONEY_SIZE;
        int yDown =  Dimensions.WINDOW_HEIGHT / 3;
        int yUp = Dimensions.WINDOW_HEIGHT / 2 + Dimensions.MARGIN;
        switch (position) {
            case 3:
                playerLayout.x = Dimensions.WINDOW_WIDTH / 4;
                playerLayout.y = Dimensions.MARGIN;
                playerLayout.movable = true;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.RIGHT;
                break;
            case 4: //lewy dół
                playerLayout.x =leftMargin;
                playerLayout.y = yDown;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.LEFT;
                break;
            case 5: // lewa góra
                playerLayout.x =leftMargin;
                playerLayout.y = yUp;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.LEFT;
                break;
            case 2: //prawy dół
                playerLayout.x = rightMargin;
                playerLayout.y = yDown;
                playerLayout.handDirection = Direction.LEFT;
                playerLayout.moneyDirection = Direction.RIGHT;
                break;
            case 1: // prawa góra
                playerLayout.x = rightMargin;
                playerLayout.y = yUp ;
                playerLayout.handDirection = Direction.LEFT;
                playerLayout.moneyDirection = Direction.RIGHT;
                break;
            case 6:
                //playerLayout.x = Dimensions.WINDOW_WIDTH / 3;
                playerLayout.x = Dimensions.CENTER_X - Dimensions.CARD_WEIGHT/2;
                playerLayout.y = Dimensions.CENTER_Y - Dimensions.CARD_HEIGHT/2;
                //playerLayout.y = Dimensions.WINDOW_HEIGHT - Dimensions.CARD_HEIGHT - Dimensions.MARGIN;
                //playerLayout.withMoney = true;
                playerLayout.handDirection = Direction.RIGHT;
                playerLayout.moneyDirection = Direction.LEFT;
                break;
            default:
                throw new IllegalArgumentException("Valid positions: 1-6");
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