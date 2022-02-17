package ap.mini_project.shared.response;

import java.util.LinkedList;

public class LiveGamesResponse extends Response{
    private LinkedList<LiveGameForm> liveGameForms;

    public LiveGamesResponse(LinkedList<LiveGameForm> liveGameForms) {
        this.liveGameForms = liveGameForms;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitLiveGames(liveGameForms);
    }
}
