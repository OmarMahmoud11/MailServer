package Controller.Profile.Elements;

import Controller.DataContainerI;
import Controller.Profile.Elements.Email.EmailI;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface ProfileSentI {
    DataContainerI SentDataContainer = null;

    ArrayList<EmailI> emails = null;
    PriorityQueue<EmailI> emailsPrioritized = null;

    ArrayList<EmailI> getEmails();

    void addEmail(EmailI email);
    void removeEmail(EmailI email);
    void removeEmailbyID(String ID);
    void setEmails(ArrayList<EmailI> emails);

    void setEmailsPrioritized(PriorityQueue<EmailI> emailsPrioritized);

    PriorityQueue<EmailI> getEmailsPrioritized();
    EmailI getEmailbySubject(String subject);
    EmailI getEmailbyBody(String body);
    EmailI getEmailbySenderUsername(String username);
    EmailI getEmailbyreceiverUsername(String username);
    EmailI getEmailbyID(String ID);


    DataContainerI getSentDataContainer();
    void setSentDataContainer(DataContainerI SentDataContainer);


}
