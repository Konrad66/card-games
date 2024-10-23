package konrad.poker.client;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.server.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements Mediator {

    private PokerService pokerService = new PokerService();
    private PokerGameRules pokerGameRules = new PokerGameRules();
    private PokerGame pokerGame;
    private DeckGroup deck;
    private Map<Integer, PlayerGroup> players = new HashMap<>();
    private GameScreen gameScreen;


    public Controller(PokerGame pokerGame, GameScreen gameScreen) {
        this.pokerGame = pokerGame;
        this.gameScreen = gameScreen;
    }

    void setupActors() {
        createPlayer();
        createDeck();
    }

    void createPlayer() {
        List<Player> playersList = pokerService.getPlayers();
        PokerGameLayout layout = new PokerGameLayout();
        for (Player player : playersList) {
            PokerGameLayout.PlayerLayout playerLayout = layout.getLayoutFor(player.getId());
            MoneyActor moneyActor = null;
            if (playerLayout.isWithMoney()) {
                moneyActor = new MoneyActor(player.getMoney(), pokerGame.getFont(), playerLayout.isMovable(), playerLayout.getMoneyDirection(), this);
            }

            HandGroup hand = new HandGroup(playerLayout.getHandDirection(), this);
            PlayerGroup playerGroup = new PlayerGroup(hand, moneyActor, player, this);
            playerGroup.setX(playerLayout.getX());
            playerGroup.setY(playerLayout.getY());
            players.put(player.getId(), playerGroup);
        }
    }

    void createDeck() {
        deck = new DeckGroup(this, this);
    }

    void startGame() {
        pokerGameRules.getStartCommands()
                .forEach(this::playCommand);
        pokerService.printStatus();
        //todo po lewej na czym wywyołujemy np nazwa klasy przy metodach statycznych, this jeśli to jest ten obiekt :: po prawej jest nazwa metyody którą chcemy wywołać, bez parametrów -
        //todo metod reference możemy użyć jeżeli nie ma parametrów lub jest oczywiste np tak jak w tym przypadku
    }

    private void playCommand(Command command) {
        boolean success = pokerService.executeCommand(command);
        if (success) {
            executeCommand(command);
        }
    }

    private void executeCommand(Command command) {
        switch (command.getType()) {
            case DRAW:
                deck.playerDraws(players.get(command.getPlayerId()), command.getAmount());
                break;
            case BID:
                players.get(command.getPlayerId()).placeBidWithAnimation(command.getAmount());
                break;
        }
    }

    @Override
    public void spawnNewThing(Actor actor) {
        gameScreen.addToStage(actor);
    }

    @Override
    public void removeThing(Actor actor) {
        gameScreen.removeFromStage(actor);
    }

    @Override
    public void increaseDealerMoney(int receivedMoney) {
        getDealer().increaseDealerMoney(receivedMoney);
    }

    @Override
    public Vector2 getDealerMoneyVector() {
        return getDealer().getMoneyVector();
    }

    public List<Card> getDeckCards() {
        return pokerService.getDeckCards();
    }

    public DeckGroup getDeck() {
        return deck;
    }

    public Map<Integer, PlayerGroup> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "player=" + players +
                ", deck=" + deck +
                '}';
    }

    private PlayerGroup getDealer() {
        return players.get(pokerGameRules.getIdBy(PlayerType.DEALER));
    }

    //controller.spawnNewThing(A a)
    //controller.playCommand(C c)
}
