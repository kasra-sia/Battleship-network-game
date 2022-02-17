package ap.mini_project.shared.response;

import ap.mini_project.shared.model.PlayerStatus;

public class PlayerScoreForm {
    private String username;
    private String score;
    private PlayerStatus playerStatus;

    public PlayerScoreForm(String username, String score, PlayerStatus playerStatus) {
        this.username = username;
        this.score = score;
        this.playerStatus = playerStatus;
    }

    public String getUsername() {
        return username;
    }

    public String getScore() {
        return score;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

}
