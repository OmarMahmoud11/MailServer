package Controller.Profile;

import Controller.DataContainerI;
import Controller.Profile.Elements.*;
import Controller.Profile.Elements.Contacts.ProfileContactsI;

import java.util.ArrayList;

public class Profile implements ProfileI {

    private String username;
    private String passWord;
    private String encryption;
    private DataContainerI dataContainer;
    private ProfileTrashI trash;
    private ProfileDraftI draft;
    private ProfileInboxI inbox;
    private ProfileSentI Sent;
    private ProfileContactsI contacts;
    private ArrayList<ProfileFolderI> folders;


    public Profile(){
        this.folders = new ArrayList<>();
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassWord() {
        return this.passWord;
    }

    @Override
    public String getEncryption() {
        return this.encryption;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    @Override
    public DataContainerI getDataContainer() {
        return this.dataContainer;
    }

    @Override
    public void setDataContainer(DataContainerI dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public ProfileTrashI getTrash() {
        return this.trash;
    }


    @Override
    public ProfileInboxI getInbox() {
        return this.inbox;
    }

    @Override
    public ProfileDraftI getDraft() {
        return this.draft;
    }

    @Override
    public ProfileSentI getSent() {
        return this.Sent;
    }

    @Override
    public ProfileContactsI getContacts() {
        return this.contacts;
    }

    @Override
    public void setTrash(ProfileTrashI trash) {
        this.trash = trash;
    }

    @Override
    public void setDraft(ProfileDraftI draft) {
        this.draft = draft;
    }

    @Override
    public void setInbox(ProfileInboxI inbox) {
        this.inbox = inbox;
    }

    @Override
    public void setSent(ProfileSentI Sent) {
        this.Sent = Sent;
    }

    @Override
    public void setContacts(ProfileContactsI contacts) {
        this.contacts = contacts;
    }

    @Override
    public ProfileFolderI getProfileFolderbyName(String name) {
        ProfileFolderI profileFolder = null;
        for(int i = 0; i < this.folders.size(); i++){
            if(name.equals(this.folders.get(i).getFolderDataContainer().getDataContainerName())){
                profileFolder = this.folders.get(i);
            }
        }
        return profileFolder;
    }
    @Override
    public void removeFolderbyName(String name) {
        ProfileFolderI profileFolder = null;
        for(int i = 0; i < this.folders.size(); i++){
            if(name.equals(this.folders.get(i).getFolderDataContainer().getDataContainerName())){
                removeFolder(this.folders.get(i));
            }
        }

    }

    public void setFolders(ArrayList<ProfileFolderI> folders) {
        this.folders = folders;
    }

    public ArrayList<ProfileFolderI> getFolders(){
        return this.folders;
    }

    @Override
    public void removeFolder(ProfileFolderI profileFolder) {
        this.folders.remove(profileFolder);
    }

    @Override
    public void addFolder(ProfileFolderI profileFolder) {
        this.folders.add(profileFolder);
    }


}
