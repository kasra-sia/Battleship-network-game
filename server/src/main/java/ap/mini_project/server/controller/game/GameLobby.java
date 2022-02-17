package ap.mini_project.server.controller.game;

import ap.mini_project.server.controller.ClientHandler;
import ap.mini_project.server.controller.game.SeaBattle.SeaBattleGame;
import ap.mini_project.shared.model.Side;

import java.util.Random;

public class GameLobby {
    private ClientHandler waiting;

    public void setWaiting(ClientHandler waiting) {
        this.waiting = waiting;
    }

    public ClientHandler getWaiting() {
        return waiting;
    }

    public GameLobby() {

    }

    public synchronized void startGameRequest(ClientHandler clientHandler) {
        if (waiting == null) {
            waiting = clientHandler;
//            clientHandler.setSide(Side.PLAYER_ONE);
        } else {
            if (waiting != clientHandler) {
                Random random = new Random();
                int temp = random.nextInt(2);
                SeaBattleGame seaBattleGame ;
                seaBattleGame = temp == 0 ?new SeaBattleGame(waiting.getOwner(), clientHandler.getOwner())
                                          : new SeaBattleGame(clientHandler.getOwner(), waiting.getOwner());
                if (temp == 0) {
                    waiting.setSide(Side.PLAYER_ONE);
                    clientHandler.setSide(Side.PLAYER_TWO);
                }else {
                    clientHandler.setSide(Side.PLAYER_ONE);
                    waiting.setSide(Side.PLAYER_TWO);
                }
//                clientHandler.setSide(Side.PLAYER_TWO);
                waiting.setGame(seaBattleGame);
                clientHandler.setGame(seaBattleGame);
                    waiting = null;

            }
        }

    }
}
