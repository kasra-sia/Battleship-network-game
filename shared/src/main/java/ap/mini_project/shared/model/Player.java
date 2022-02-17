package ap.mini_project.shared.model;

public class Player {
    private int ID;
    private String username;
    private String password;
    private int score;
    private int winnings;
    private int loses;
    private PlayerStatus playerStatus ;


    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }
    public void increaseScore(){
        score++;
        winnings++;
    }
    public void decreaseScore(){
        score--;
        loses++;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public int getWinnings() {
        return winnings;
    }

    public void setWinnings(int winnings) {
        this.winnings = winnings;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }
}
