package Controller.EmailsFilter;

import Controller.Profile.Elements.Email.EmailI;

import java.util.ArrayList;

public class EmailsSearchingCustomizedCriteria implements EmailsCriteriaI {
    private String target;

    public EmailsSearchingCustomizedCriteria(String target){
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public ArrayList<EmailI> meetCriteria(ArrayList<EmailI> emails) {
        ArrayList<EmailI> filteredEmails = new ArrayList<>();
        for(EmailI email:emails){
            if((email.getBody().toLowerCase()).concat(email.getSubject().toLowerCase()
            .concat(email.getSenderUsername().toLowerCase().concat(email.getReceiversUsernames().get(0).toLowerCase()
            .concat(email.getTimeSentString().toLowerCase().concat(email.getPriority().toLowerCase()
            ))))).contains(this.target.toLowerCase())){
                filteredEmails.add(email);
            }
        }
        return filteredEmails;
    }
}
