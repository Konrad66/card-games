package konrad.poker.client;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import konrad.poker.client.actors.*;
import konrad.poker.server.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements Mediator {

    private GameService gameService;
    private CardGame cardGame;
    private DeckGroup deck;
    private Map<Integer, PlayerGroup> players = new HashMap<>();
    private GameScreen gameScreen;


    public Controller(CardGame cardGame, GameScreen gameScreen, GameService gameService) {
        this.cardGame = cardGame;
        this.gameScreen = gameScreen;
        this.gameService = gameService;
    }

    void setupActors() {
        createPlayer();
        createDeck();
    }

    void createPlayer() {
        List<Player> playersList = gameService.getPlayers();
        PokerGameLayout layout = new PokerGameLayout();
        for (Player player : playersList) {
            PokerGameLayout.PlayerLayout playerLayout = layout.getLayoutFor(player.getId());
            MoneyActor moneyActor = null;
            if (playerLayout.isWithMoney()) {
                moneyActor = new MoneyActor(player.getMoney(), cardGame.getFont(), playerLayout.isMovable(), playerLayout.getMoneyDirection(), this);
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
        gameService.getStartCommands()
                .forEach(this::playCommand);
        gameService.printStatus();
        //todo po lewej na czym wywyołujemy np nazwa klasy przy metodach statycznych, this jeśli to jest ten obiekt :: po prawej jest nazwa metyody którą chcemy wywołać, bez parametrów -
        //todo metod reference możemy użyć jeżeli nie ma parametrów lub jest oczywiste np tak jak w tym przypadku
    }

    private void playCommand(Command command) { //call -> amount -1 id 3
        boolean success = gameService.executeCommand(command);
        if (success) {
            executeCommand(command);
        }
    }

    private void executeCommand(Command command) {
        switch (command.getType()) {
            case DRAW:
                deck.playerDraws(players.get(command.getPlayerId()), command.getAmount());
                break;
            case RISE:
            case CALL:
                players.get(command.getPlayerId()).placeBidWithAnimation(command.getPlayerBid());
                break;
            case CHECK:
                break;
            case FOLD:
                //todo wyszarzyć gracza w momencie użycia tej komendy
                break;
        }
    }
//

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
    public void executeHumanCommand(CommandType commandType, int amount) {
        Command command = new Command(commandType, amount, getHumanId());
        System.out.println(command);
        playCommand(command);
        //todo dokończyć
    }


    @Override
    public Vector2 getDealerMoneyVector() {
        return getDealer().getMoneyVector();
    }

    public List<Card> getDeckCards() {
        return gameService.getDeckCards();
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
        return players.get(gameService.getIdBy(PlayerType.DEALER));
    }

    public PlayerGroup getHumanPlayer(){
        return players.get(getHumanId());
    }

    private int getHumanId(){
        return gameService.getIdBy(PlayerType.HUMAN);
    }

    public void addControlButtonActions(){
        //todo button action
    }

    //controller.spawnNewThing(A a)
    //controller.playCommand(C c)
}
