package Controller.SingletonClasses.Handlers;

import Controller.Profile.Elements.Email.EmailI;
import Controller.Profile.ProfileI;
import Controller.SingletonClasses.Creator;
import Controller.SingletonClasses.Database;

import java.util.UUID;


public class ThirdHandler implements HandlerI {

    private final String concern = "MovetoDraft";
    private HandlerI successor = FourthHandler.getInstance();
    private static ThirdHandler instance = null;

    private ThirdHandler(){}
    public static ThirdHandler getInstance(){
        if(instance == null){
            return new ThirdHandler();
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
            String emailDraftId = UUID.randomUUID().toString();
            Creator.getInstance().createEmailDataDraft(email, owner, emailDraftId);
            System.out.println("OWNER" + owner.getUsername());

        }else{
            if(this.successor == null){
                throw new Exception("NO HANDLER CAN HANDLE THIS CONCERN");
            }
            this.successor.handle(concern, email, folderName);
        }
    }
}
