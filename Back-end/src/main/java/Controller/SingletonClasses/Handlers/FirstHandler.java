package Controller.SingletonClasses.Handlers;

import Controller.Profile.Elements.Email.EmailI;
import Controller.Profile.ProfileI;
import Controller.SingletonClasses.Creator;
import Controller.SingletonClasses.Database;
import Controller.SingletonClasses.Deleter;

import java.util.UUID;

public class FirstHandler implements HandlerI{
    private final String concern = "SendEmail";
    private HandlerI successor = SecondHandler.getInstance();
    private static FirstHandler instance = null;

    private FirstHandler(){}
    public static FirstHandler getInstance(){
        if(instance == null){
            return new FirstHandler();
        }else {
            return instance;
        }
    }

    public void handle(String concern, EmailI email, String folderName)throws Exception{
        if(concern == this.concern){
            Database database = Database.getInstance();
            if(database.getProfilebyUsername("", email.getSenderUsername()) == null){
                throw new Exception("THERE IS NO SENDER BY THIS USERNAME");
            }

            String oldDraftID = email.getEmailID();
            Creator creator = Creator.getInstance();
            String senderID = UUID.randomUUID().toString();
            ProfileI sender = database.getProfilebyUsername("", email.getSenderUsername());
            sender.getSent().addEmail(creator.createEmailDataSent(email, sender, senderID));

            for(int i = 0; i < email.getReceiversUsernames().size(); i++){
                String username = email.getReceiversUsernames().get(i);
                if(database.getProfilebyUsername("", username) != null){
                    ProfileI reciever = database.getProfilebyUsername("", username);
                    String recieverID = UUID.randomUUID().toString();
                    reciever.getInbox().addEmail(creator.createEmailDataInbox(email, reciever, recieverID, i));
                }
            }
            if(email.getEmailType() != null){
                if(email.getEmailType().equals("Draft")){
                    System.out.println("OLD ID " + email.getEmailID());
                    Deleter.getInstance().deleteEmailDataDraft(email, sender);
                    sender.getDraft().removeEmailbyID(oldDraftID);
                }
            }

        }else{
            if(this.successor == null){
                throw new Exception("NO HANDLER CAN HANDLE THIS CONCERN");
            }
            this.successor.handle(concern,email, folderName);
        }
    }

}
