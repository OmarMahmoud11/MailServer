package Controller.SingletonClasses;


import Controller.DataContainer;
import Controller.DataContainerI;
import Controller.Profile.Elements.Email.Email;
import Controller.Profile.Elements.Email.EmailI;
import Controller.Profile.Builder.ProfileBuilder;
import Controller.Profile.Builder.ProfileBuilderI;
import Controller.Profile.Builder.ProfileDirector;
import Controller.Profile.Elements.*;
import Controller.Profile.Elements.Contacts.ContactI;
import Controller.Profile.Elements.Contacts.ProfileContacts;
import Controller.Profile.Profile;
import Controller.Profile.ProfileI;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

public class Creator {

    private static Creator instance;

    private Creator() {}
    public static Creator getInstance(){
        if(instance == null){
            instance = new Creator();
            System.out.println("CREATED CREATOR");
        }
        return instance;
    }

    private static DataContainerI createDataContainer(String dataContainerPath, String dataContainerName) throws Exception{
        File file = new File(dataContainerPath);
        boolean createdFile = file.mkdir();
        if(createdFile){
            return new DataContainer(dataContainerPath, dataContainerName, file);
        }
        else{
            throw new Exception("COULD NOT CREATE DATA CONTAINER");
        }
    }


    public ProfileI createProfile(String dataBasePath, String encryption) throws Exception{
        ProfileBuilderI profileBuilder = new ProfileBuilder();
        ProfileDirector profileDirector = new ProfileDirector();

        profileDirector.buildProfileData(profileBuilder, encryption);
        profileDirector.buildDataContainer(profileBuilder, createDataContainer(dataBasePath.concat(encryption), encryption));

        profileDirector.buildInbox(profileBuilder, createDataContainer(dataBasePath.concat(encryption).concat("/Inbox"), "Inbox"));
        profileDirector.buildSent(profileBuilder, createDataContainer(dataBasePath.concat(encryption).concat("/Sent"), "Sent"));
        profileDirector.buildDraft(profileBuilder, createDataContainer(dataBasePath.concat(encryption).concat("/Draft"), "Draft"));
        profileDirector.buildTrash(profileBuilder, createDataContainer(dataBasePath.concat(encryption).concat("/Trash"), "Trash"));
        profileDirector.buildContacts(profileBuilder, createDataContainer(dataBasePath.concat(encryption).concat("/Contacts"), "Contacts"));

        return profileBuilder.getProfile();
    }

    public ProfileI setProfile(String dataBasePath, String encryption) throws Exception{
        ProfileI profile = new Profile();

        File file = new File(dataBasePath.concat(encryption).concat("/"));
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        profile.setDataContainer(new DataContainer(dataBasePath.concat(encryption), encryption, new File(dataBasePath.concat(encryption))));
        profile.setEncryption(encryption);
        profile.setUsername(encryption.substring(0, encryption.indexOf("$")));
        profile.setPassWord(encryption.substring(encryption.indexOf("$") + 1));
        for(int i = 0 ; i < files.length; i++){
            System.out.println(files[i].getName());
            if(files[i].getName().equals("Inbox")){
                profile.setInbox(new ProfileInbox(new DataContainer(dataBasePath.concat(encryption).concat("/Inbox"), "Inbox", new File(dataBasePath.concat(encryption).concat("/Inbox")))));
            }
            else if(files[i].getName().equals("Sent")){
                profile.setSent(new ProfileSent(new DataContainer(dataBasePath.concat(encryption).concat("/Sent"), "Sent", new File(dataBasePath.concat(encryption).concat("/Sent")))));
            }
            else if(files[i].getName().equals("Draft")){
                profile.setDraft(new ProfileDraft(new DataContainer(dataBasePath.concat(encryption).concat("/Draft"), "Draft", new File(dataBasePath.concat(encryption).concat("/Draft")))));
            }
            else if(files[i].getName().equals("Trash")){
                profile.setTrash(new ProfileTrash(new DataContainer(dataBasePath.concat(encryption).concat("/Trash"), "Trash", new File(dataBasePath.concat(encryption).concat("/Trash")))));
            }
            else if(files[i].getName().equals("Contacts")){
                profile.setContacts(new ProfileContacts(new DataContainer(dataBasePath.concat(encryption).concat("/Contacts"), "Contacts", new File(dataBasePath.concat(encryption).concat("/Contacts")))));
            }
            else{
                profile.addFolder(new ProfileFolder(new DataContainer(dataBasePath.concat(encryption).concat("/").concat(files[i].getName()), files[i].getName(), new File(dataBasePath.concat(encryption).concat("/").concat(files[i].getName()))), files[i].getName()));
            }
        }
        return profile;
    }

    public void createProfileFolder(String name, ProfileI profile) throws Exception{
        System.out.println(profile.getDataContainer());
        ProfileFolderI profileFolder = new ProfileFolder(createDataContainer(profile.getDataContainer().getDataContainerPath().concat("/").concat(name), name), name);
        profile.addFolder(profileFolder);

    }

    public void createDataFile(ProfileI profile) throws Exception{
        File file = new File(profile.getDataContainer().getDataContainerPath().concat("/PROFILE.json"));
        if(!file.createNewFile()){
            throw new Exception("COULD NOT CREATE PROFILE DATA FILE");
        }
        ObjectMapper map = new ObjectMapper();
        map.writeValue(file, profile);
    }

    public EmailI createEmailDataInbox(EmailI email, ProfileI profile, String ID, int receiverIndex) throws Exception{
        EmailI inboxEmail = new Email(email.getSubject(), email.getBody(), email.getSenderUsername(), new ArrayList<String>(Collections.singleton(email.getReceiversUsernames().get(receiverIndex))), "Inbox", email.getAttachments(), email.getPriority());
        File file = new File(profile.getInbox().getInboxDataContainer().getDataContainerPath().concat("/").concat(ID).concat(".json"));
        if(!file.createNewFile()){
            throw new Exception("COULD NOT CREATE INBOX EMAIL FILE");
        }
        inboxEmail.setEmailID(ID);
        if(receiverIndex != -1){
            inboxEmail.setOwner(email.getReceiversUsernames().get(receiverIndex));
        }
        inboxEmail.setEmailType("Inbox");
        ObjectMapper map = new ObjectMapper();
        map.writeValue(file, inboxEmail);
        return inboxEmail;
    }
    public EmailI createEmailDataSent(EmailI email, ProfileI profile, String ID) throws Exception{
        EmailI sentEmail = new Email(email.getSubject(), email.getBody(), email.getSenderUsername(), email.getReceiversUsernames(), "Sent", email.getAttachments(), email.getPriority());
        File file = new File(profile.getSent().getSentDataContainer().getDataContainerPath().concat("/").concat(ID).concat(".json"));
        if(!file.createNewFile()){
            throw new Exception("COULD NOT CREATE Sent EMAIL FILE");
        }
        sentEmail.setEmailID(ID);
        sentEmail.setOwner(email.getOwner());
        sentEmail.setEmailType("Sent");
        ObjectMapper map = new ObjectMapper();
        map.writeValue(file, sentEmail);
        return sentEmail;
    }
    public EmailI createEmailDataDraft(EmailI email, ProfileI profile, String ID) throws Exception{
        EmailI draftEmail = new Email(email.getSubject(), email.getBody(), email.getSenderUsername(), email.getReceiversUsernames(), "Draft", email.getAttachments(), email.getPriority());
        File file = new File(profile.getDraft().getDraftDataContainer().getDataContainerPath().concat("/").concat(ID).concat(".json"));
        if(!file.createNewFile()){
            throw new Exception("COULD NOT CREATE TRASH EMAIL FILE");
        }
        draftEmail.setEmailID(ID);
        draftEmail.setOwner(email.getOwner());
        ObjectMapper map = new ObjectMapper();
        map.writeValue(file, draftEmail);
        Database.getInstance().getProfilebyUsername("", email.getOwner()).getDraft().addEmail(draftEmail);
        return draftEmail;
    }
    public EmailI createEmailDataTrash(EmailI email, ProfileI profile, String ID) throws Exception{
        EmailI trashEmail = new Email(email.getSubject(), email.getBody(), email.getSenderUsername(), email.getReceiversUsernames(), "Trash", email.getAttachments(), email.getPriority());
        File file = new File(profile.getTrash().getTrashDataContainer().getDataContainerPath().concat("/").concat(ID).concat(".json"));
        if(!file.createNewFile()){
            throw new Exception("COULD NOT CREATE TRASH EMAIL FILE");
        }
        trashEmail.setEmailID(ID);
        trashEmail.setOwner(email.getOwner());
        trashEmail.setEmailType(email.getEmailType());
        ObjectMapper map = new ObjectMapper();
        map.writeValue(file, trashEmail);
        return trashEmail;
    }

    public EmailI createEmailDataProfileFolder(EmailI email, ProfileI profile, String folderName, String ID) throws Exception {
        if(profile.getProfileFolderbyName(folderName) == null){
            throw new Exception("NO FOLDER BY THIS NAME BELONING TO THIS PROFILE");
        }
        EmailI emailFolder = new Email(email.getSubject(), email.getBody(), email.getSenderUsername(), email.getReceiversUsernames(), folderName, email.getAttachments(), email.getPriority());
        File file = new File(profile.getProfileFolderbyName(folderName).getFolderDataContainer().getDataContainerPath().concat("/").concat(ID).concat(".json"));
        if(!file.createNewFile()){
            throw new Exception("COULD NOT CREATE FOLDER EMAIL FILE");
        }
        emailFolder.setOwner(email.getOwner());
        emailFolder.setEmailID(ID);
        emailFolder.setEmailType(folderName);
        ObjectMapper map = new ObjectMapper();
        map.writeValue(file, emailFolder);
        return emailFolder;
    }

    public void createContactData(ProfileI profile, ContactI contact) throws Exception{
        File file = new File(profile.getContacts().getContactsDataContainer().getDataContainerPath().concat("/").concat(contact.getUsername().concat(".json")));
        if(!file.createNewFile()){
            throw new Exception("COULD NOT CREATE CONTACT FOLDER");
        }
        ObjectMapper map = new ObjectMapper();
        map.writeValue(file, contact);
    }


}
