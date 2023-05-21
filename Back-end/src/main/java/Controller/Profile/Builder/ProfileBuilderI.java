package Controller.Profile.Builder;

import Controller.DataContainerI;
import Controller.Profile.*;
import Controller.Profile.Elements.*;
import Controller.Profile.Elements.Contacts.ProfileContactsI;

public interface ProfileBuilderI {
    void setUsername(String username);
    void setPassWord(String passWord);
    void setEncryption(String encryption);
    void setDataContainer(DataContainerI dataContainer);
    void setTrash(ProfileTrashI trash);
    void setDraft(ProfileDraftI draft);
    void setInbox(ProfileInboxI inbox);
    void setSent(ProfileSentI Sent);
    void setContacts(ProfileContactsI contacts);

    ProfileI getProfile();
}
