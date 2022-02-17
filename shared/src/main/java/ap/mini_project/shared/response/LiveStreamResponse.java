package ap.mini_project.shared.response;

public class LiveStreamResponse extends Response{
    private LiveGameForm liveGameForm;

    public LiveStreamResponse(LiveGameForm liveGameForm) {
        this.liveGameForm = liveGameForm;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitLiveStream(liveGameForm);
    }
}
