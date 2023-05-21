package Controller.Profile.Elements.Email;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class Email implements EmailI {

    private String subject;
    private String body;

    private String owner;
    private String senderUsername;
    private ArrayList<String> receiversUsernames;
    private String priority;

    private String timeSent;
    private String timeSentString;
    private String emailID;

    private String emailType;
    private ArrayList<Attachment> attachments = null;


    @Override
    public String getTimeSentString() {
        return timeSentString;
    }

    @Override
    public void setTimeSentString(String timeSentString) {
        this.timeSentString = timeSentString;
    }


    public ArrayList<String> getReceiversUsernames() {
        return this.receiversUsernames;
    }

    public void setReceiversUsernames(ArrayList<String> receiversUsernames) {
        this.receiversUsernames = receiversUsernames;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Email(){}
    public Email(String subject, String body, String senderUsername, ArrayList<String> receiversUsernames, String emailType, ArrayList<Attachment> attachments, String priority){
        this.senderUsername = senderUsername;
        this.receiversUsernames = receiversUsernames;
        this.subject = subject;
        this.body = body;
        this.timeSent = String.valueOf(new Date().getTime());
        this.timeSentString = SimpleDateFormat.getDateTimeInstance().format(new Date());
        this.emailType = emailType;
        this.attachments = attachments;
        this.priority = priority;
    }
    @Override
    public String getSenderUsername() {
        return this.senderUsername;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String getEmailID() {
        return this.emailID;
    }

    @Override
    public String getTimeSent() {
        return this.timeSent;
    }

    @Override
    public String getEmailType() {
        return this.emailType;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public ArrayList<Attachment> getAttachments() {
        return this.attachments;
    }

    @Override
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @Override
    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    @Override
    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }


}
