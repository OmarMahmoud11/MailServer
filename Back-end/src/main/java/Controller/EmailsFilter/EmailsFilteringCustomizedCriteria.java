package Controller.EmailsFilter;

import Controller.Profile.Elements.Email.EmailI;

import java.util.ArrayList;

public class EmailsFilteringCustomizedCriteria implements EmailsCriteriaI {
    private String feature;
    private String target;

    public EmailsFilteringCustomizedCriteria(String feature, String target){
        this.feature = feature;
        this.target = target;
    }

    public String getFeature() {
        return feature;
    }

    public String getTarget() {
        return target;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public ArrayList<EmailI> meetCriteria(ArrayList<EmailI> emails) {
        ArrayList<EmailI> filteredEmails = new ArrayList<>();
        switch (this.feature.toLowerCase()){
            case "subject":
                for(EmailI email:emails){
                    if(email.getSubject().equalsIgnoreCase(this.target)){
                        filteredEmails.add(email);
                    }
                }
                break;
            case "senderUsername":
                for(EmailI email:emails){
                    if(email.getSenderUsername().equalsIgnoreCase(this.target)){
                        filteredEmails.add(email);
                    }
                }
                break;

        }
        return filteredEmails;
    }
}
