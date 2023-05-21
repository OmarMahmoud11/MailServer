package Controller.SingletonClasses.Handlers;

import Controller.Profile.Elements.Email.EmailI;
import Controller.Profile.ProfileI;
import Controller.SingletonClasses.Creator;
import Controller.SingletonClasses.Database;
import Controller.SingletonClasses.Deleter;

import java.util.UUID;

public class FourthHandler implements HandlerI{

    private final String concern = "MoveToFolder";
    private HandlerI successor = FifthHandler.getInstance();
    private static FourthHandler instance = null;

    private FourthHandler(){}
    public static FourthHandler getInstance(){
        if(instance == null){
            return new FourthHandler();
        }else {
            return instance;
        }
    }

    @Override
    public void handle(String concern, EmailI email, String folderName) throws Exception {
        if(concern == this.concern){
            Database database = Database.getInstance();
            if(database.getProfilebyUsername("", email.getOwner()) == null){
                throw new Exception("THERE IS NO PROFILE BY THIS USERNAME");
            }
            ProfileI owner = database.getProfilebyUsername("", email.getOwner());

            String ID = UUID.randomUUID().toString();

            if(email.getEmailType().equals("Inbox")){
                owner.getProfileFolderbyName(folderName).addEmail(Creator.getInstance().createEmailDataProfileFolder(email, owner, folderName, ID));

            }
            if(email.getEmailType().equals("Sent")){
                owner.getProfileFolderbyName(folderName).addEmail(Creator.getInstance().createEmailDataProfileFolder(email, owner, folderName, ID));

            }
            if(email.getEmailType().equals("Draft")){
                owner.getProfileFolderbyName(folderName).addEmail(Creator.getInstance().createEmailDataProfileFolder(email, owner, folderName, ID));
            }

        }else{
            if(this.successor == null){
                throw new Exception("NO HANDLER CAN HANDLE THIS CONCERN");
            }
            this.successor.handle(concern, email, folderName);
        }

    }

}
