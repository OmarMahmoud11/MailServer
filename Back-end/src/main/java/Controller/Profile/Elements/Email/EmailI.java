package Controller.Profile.Elements.Email;

import java.text.DateFormat;
import java.util.ArrayList;

public interface EmailI {
    String subject = null;
    String body = null;
    String owner = null;
    String senderUsername = null;
    ArrayList<String> receiversUsernames = null;
    String priority = null;
    String emailID = null;
    String emailType = null;
    ArrayList<Attachment> attachments = null;

    DateFormat timeSent = null;
    String timeSentString = null;

    String getSenderUsername();
    ArrayList<String> getReceiversUsernames();
    String getSubject();
    String getBody();
    String getEmailID();
    String getTimeSent();
    String getTimeSentString();
    String getEmailType();
    String getOwner();
    String getPriority();
    ArrayList<Attachment> getAttachments();


    void setSenderUsername(String senderUsername);
    void setReceiversUsernames(ArrayList<String> receiversUsernames);
    void setSubject(String subject);
    void setTimeSent(String timeSent);
    void setTimeSentString(String timeSentString);
    void setBody(String body);
    void setEmailID(String emailID);
    void setEmailType(String emailType);
    void setOwner(String owner);
    void setPriority(String priority);
    void setAttachments(ArrayList<Attachment> attachments);


    void addAttachment(Attachment attachment);
    void removeAttachment(Attachment attachment);





}
