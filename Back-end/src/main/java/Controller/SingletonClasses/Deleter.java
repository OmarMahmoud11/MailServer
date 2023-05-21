package Controller.SingletonClasses;

import Controller.DataContainerI;
import Controller.Profile.Elements.Email.EmailI;
import Controller.Profile.Elements.Contacts.ContactI;
import Controller.Profile.ProfileI;

import java.io.File;
import java.io.FileFilter;

public class Deleter {
    private static Deleter instance;
    private Deleter(){}

    public static Deleter getInstance(){
        if(instance == null){
            instance = new Deleter();
            System.out.println("CREATED DELETER");
        }

        return instance;
    }


    private static void deleteDataContainer(DataContainerI dataContainer) throws Exception{
        boolean deletedFile = dataContainer.getFile().delete();
        if(!deletedFile){
            throw new Exception("COULD NOT DELETE DATACONTAINER");
        }
    }

    public void deleteProfile(ProfileI profile) throws Exception{
        deleteDataContainer(profile.getTrash().getTrashDataContainer());
        deleteDataContainer(profile.getDraft().getDraftDataContainer());
        deleteDataContainer(profile.getInbox().getInboxDataContainer());
        deleteDataContainer(profile.getSent().getSentDataContainer());
        deleteDataContainer(profile.getContacts().getContactsDataContainer());
        deleteDataContainer(profile.getDataContainer());

    }

    public void deleteEmailDataInbox(EmailI email, ProfileI profile) throws Exception{
        if(profile.getInbox().getEmailbyID(email.getEmailID()) == null){
            throw new Exception("NO SUCH EMAIL TO DELETE");
        }
        File file = new File(profile.getInbox().getInboxDataContainer().getDataContainerPath().concat("/").concat(email.getEmailID()).concat(".json"));
        if(!file.delete()){
            throw new Exception("COULD NOT DELETE EMAIL");
        }

    }
    public void deleteEmailDataSent(EmailI email, ProfileI profile) throws Exception{
        if(profile.getSent().getEmailbyID(email.getEmailID()) == null){
            throw new Exception("NO SUCH EMAIL TO DELETE");
        }
        File file = new File(profile.getSent().getSentDataContainer().getDataContainerPath().concat("/").concat(email.getEmailID()).concat(".json"));
        if(!file.delete()){
            throw new Exception("COULD NOT DELETE EMAIL");
        }
    }
    public void deleteEmailDataDraft(EmailI email, ProfileI profile) throws Exception{
        if(profile.getDraft().getEmailbyID(email.getEmailID()) == null){
            throw new Exception("NO SUCH EMAIL TO DELETE");
        }
        File file = new File(profile.getDraft().getDraftDataContainer().getDataContainerPath().concat("/").concat(email.getEmailID()).concat(".json"));
        if(!file.delete()){
            throw new Exception("COULD NOT DELETE EMAIL");
        }
    }
    public void deleteEmailDataTrash(EmailI email, ProfileI profile) throws Exception{
        if(profile.getTrash().getEmailbyID(email.getEmailID()) == null){
            throw new Exception("NO SUCH EMAIL TO DELETE");
        }
        File file = new File(profile.getTrash().getTrashDataContainer().getDataContainerPath().concat("/").concat(email.getEmailID()).concat(".json"));
        if(!file.delete()){
            throw new Exception("COULD NOT DELETE EMAIL");
        }
    }
    public void deleteProfileFolder(ProfileI profile, String folderName) throws Exception{
        if(profile.getProfileFolderbyName(folderName) == null){
            throw new Exception("NO FOLDER BY THIS NAME BELONING TO THIS PROFILE");
        }
        File file = new File(profile.getProfileFolderbyName(folderName).getFolderDataContainer().getDataContainerPath().concat("/"));
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        for(int i = 0; i < files.length; i++){
            files[i].delete();
        }
        if(!profile.getProfileFolderbyName(folderName).getFolderDataContainer().getFile().delete()){
            throw new Exception("COULD NOT DELETE FOLDER");
        }
        profile.removeFolderbyName(folderName);
    }

    public void deleteEmailProfileFolder(ProfileI profile, String folderName, EmailI email) throws Exception{
        if(profile.getProfileFolderbyName(folderName).getEmailbyID(email.getEmailID()) == null){
            throw new Exception("NO SUCH EMAIL TO DELETE");
        }
        File file = new File(profile.getProfileFolderbyName(folderName).getFolderDataContainer().getDataContainerPath().concat("/").concat(email.getEmailID()).concat(".json"));
        if(!file.delete()){
            throw new Exception("COULD NOT DELETE EMAIL");
        }
    }

    public void deleteContact(ContactI contact, ProfileI profile) throws Exception{
        if(profile.getContacts().getContact(contact.getUsername()) == null){
            throw new Exception("NO SUCH CONTACT FOR THIS PROFILE");
        }
        File file = new File(profile.getContacts().getContactsDataContainer().getDataContainerPath().concat("/").concat(contact.getUsername().concat(".json")));
        if(!file.delete()){
            throw new Exception("COULD NOT DELETE CONTACT");
        }

    }

}
