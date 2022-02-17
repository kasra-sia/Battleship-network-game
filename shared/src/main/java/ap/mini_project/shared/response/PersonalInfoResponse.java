package ap.mini_project.shared.response;

public class PersonalInfoResponse extends Response{
   private PersonalInfoForm personalInfoForm;

    public PersonalInfoResponse(PersonalInfoForm personalInfoForm) {
        this.personalInfoForm = personalInfoForm;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitPersonalInfo(personalInfoForm);
    }
}
