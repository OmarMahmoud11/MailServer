package Controller.Profile.Elements;

import Controller.DataContainerI;
import Controller.Profile.Elements.Email.EmailI;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface ProfileFolderI {
    String name = null;
    DataContainerI folderDataContainer = null;
    ArrayList<EmailI> email = null;
    PriorityQueue<EmailI> emailsPrioritized = null;

    ArrayList<EmailI> getEmails();

    void addEmail(EmailI email);
    void removeEmail(EmailI email);
    void removeEmailbyID(String ID);

    void setEmails(ArrayList<EmailI> emails);

    void setEmailsPrioritized(PriorityQueue<EmailI> emailsPrioritized);

    PriorityQueue<EmailI> getEmailsPrioritized();


    String getName();
    void setName(String name);

    EmailI getEmailbySubject(String subject);
    EmailI getEmailbyBody(String body);
    EmailI getEmailbySenderUsername(String username);
    EmailI getEmailbyreceiverUsername(String username);
    EmailI getEmailbyID(String ID);


    DataContainerI getFolderDataContainer();
    void setFolderDataContainer(DataContainerI folderDataContainer);



}
