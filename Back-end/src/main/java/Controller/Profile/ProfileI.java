package Controller.Profile;

import Controller.DataContainerI;
import Controller.Profile.Elements.*;
import Controller.Profile.Elements.Contacts.ProfileContactsI;

import java.util.ArrayList;

public interface ProfileI {
    String username = null;
    String passWord = null;
    String encryption = null;
    DataContainerI dataContainer = null;
    ProfileTrashI trash = null;
    ProfileDraftI draft = null;
    ProfileInboxI inbox = null;
    ProfileSentI Sent = null;
    ProfileContactsI contacts = null;
    ArrayList<ProfileFolderI> folders = null;

    String getUsername();
    String getPassWord();
    String getEncryption();
    DataContainerI getDataContainer();
    ArrayList<ProfileFolderI> getFolders();

    void setUsername(String username);
    void setPassWord(String passWord);
    void setEncryption(String encryption);
    void setDataContainer(DataContainerI dataContainer);
    void setFolders(ArrayList<ProfileFolderI> folders);

    ProfileTrashI getTrash();
    ProfileInboxI getInbox();
    ProfileDraftI getDraft();
    ProfileSentI getSent();
    ProfileContactsI getContacts();


    void setTrash(ProfileTrashI trash);
    void setDraft(ProfileDraftI draft);
    void setInbox(ProfileInboxI inbox);
    void setSent(ProfileSentI Sent);
    void setContacts(ProfileContactsI contacts);

    ProfileFolderI getProfileFolderbyName(String name);

    void removeFolderbyName(String name);
    void removeFolder(ProfileFolderI profileFolder);
    void addFolder(ProfileFolderI profileFolder);

}
