package Controller.Profile.Elements.Contacts;

import Controller.DataContainerI;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ProfileContacts implements ProfileContactsI {

    private DataContainerI contactsDataContainer;

    private ArrayList<ContactI> contacts;

    public ProfileContacts(DataContainerI contactsDataContainer) throws Exception{
        this.contactsDataContainer = contactsDataContainer;
        this.contacts = new ArrayList<ContactI>();
        this.setContacts();
    }
    private void setContacts() throws Exception {
        File file = new File(this.contactsDataContainer.getDataContainerPath().concat("/"));
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        if(file == null || files == null){
            throw new Exception("NO SUCH DIRECOTRY");
        }
        for(int i = 0; i < files.length; i++){
            ObjectMapper map = new ObjectMapper();
            ContactI contact = map.readValue(files[i], Contact.class);
            this.addContact(contact);
        }
    }
    @Override
    public DataContainerI getContactsDataContainer() {
        return contactsDataContainer;
    }

    @Override
    public ArrayList<ContactI> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactI> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void addContact(ContactI contact) {
        contacts.add(contact);
    }

    @Override
    public ContactI getContact(String username) {
        for(int i = 0 ;i < contacts.size(); i++){
            if(contacts.get(i).getUsername().equals(username)){
                return contacts.get(i);
            }
        }
        return null;
    }

    @Override
    public void removeContact(String username) {
       contacts.remove(this.getContact(username));
    }
}
