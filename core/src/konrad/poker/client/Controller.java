package konrad.poker.client;

import konrad.poker.server.Card;
import konrad.poker.server.Player;
import konrad.poker.server.PokerService;

import static konrad.poker.client.PokerGame.WINDOW_SIZE;

public class Controller {

    private PokerService pokerService = new PokerService();
    private PokerGame pokerGame;

    public Controller(PokerGame pokerGame) {
        this.pokerGame = pokerGame;
    }

     PlayerGroup createPlayer(){
        Player player = pokerService.getPlayer();
        MoneyActor moneyActor = new MoneyActor(player,pokerGame.getFont());
        HandGroup hand = new HandGroup();
        CardActor card1 = new CardActor(player.getCard1());
        CardActor card2 = new CardActor(player.getCard2());
        hand.addActor(card1);
        hand.addActor(card2);
        PlayerGroup playerGroup = new PlayerGroup(hand,moneyActor);
        return playerGroup;
    }

    public Card getDeckCard(int i) {
        return pokerService.getDeckCard(i);
    }
}
