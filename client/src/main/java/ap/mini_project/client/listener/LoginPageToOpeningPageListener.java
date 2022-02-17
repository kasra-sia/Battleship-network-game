package ap.mini_project.client.listener;


import ap.mini_project.client.graphic.GraphicalAgent;


public class LoginPageToOpeningPageListener implements StringEventListener {
    private GraphicalAgent graphicalAgent;

    public LoginPageToOpeningPageListener(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    @Override
    public void listen(String stringEvent) {
        if (stringEvent.equals("openLoginPage")){
            graphicalAgent.goToLoginPage();
        }
    }
}
