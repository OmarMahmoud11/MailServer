package Controller.Profile.Elements.Contacts;

import java.util.ArrayList;

public interface ContactI {
    String username = null;
    ArrayList<String> emailAddresses = null;

    ArrayList<String> getEmailAddresses();
    void setEmailAddresses(ArrayList<String> emailAddresses);
    void addEmailAddress(String emailAddress);
    String getUsername() ;
    void setUsername(String username);


}
