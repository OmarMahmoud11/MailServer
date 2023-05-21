package Controller.Profile.Elements.Contacts;

import java.util.ArrayList;

public class Contact implements ContactI {
    private String username;

    private ArrayList<String> emailAddresses;

    public Contact(){}

    public Contact(String username,String phoneNumber, ArrayList<String> emailAddresses){
        this.username = username;
        this.emailAddresses = emailAddresses;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getEmailAddresses() { return this.emailAddresses; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmailAddresses(ArrayList<String> emailAddresses) { this.emailAddresses = emailAddresses; }

    public void addEmailAddress(String emailAddress){ this.emailAddresses.add(emailAddress); }
}
