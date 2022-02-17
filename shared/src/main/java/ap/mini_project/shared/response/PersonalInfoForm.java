package ap.mini_project.shared.response;

public class PersonalInfoForm {
    private int winnings,loses,score;
    private String username;

    public PersonalInfoForm(int winnings, int loses, int score, String username) {
        this.winnings = winnings;
        this.loses = loses;
        this.score = score;
        this.username = username;
    }

    public int getWinnings() {
        return winnings;
    }

    public int getLoses() {
        return loses;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }
}
