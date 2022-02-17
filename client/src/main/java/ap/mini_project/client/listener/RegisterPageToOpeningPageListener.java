package ap.mini_project.client.listener;

import ap.mini_project.client.graphic.GraphicalAgent;

public class RegisterPageToOpeningPageListener implements StringEventListener{
    private GraphicalAgent graphicalAgent;

    public RegisterPageToOpeningPageListener(GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
    }

    @Override
    public void listen(String stringEvent) {
      if (stringEvent.equals("openRegistrationPage"))
          graphicalAgent.goToRegistrationPage();
    }
}
