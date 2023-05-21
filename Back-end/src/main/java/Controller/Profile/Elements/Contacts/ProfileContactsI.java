package Controller.Profile.Elements.Contacts;

import Controller.DataContainerI;

import java.util.ArrayList;

public interface ProfileContactsI {
    DataContainerI contactsDataContainer = null;

    ArrayList<ContactI> contacts = null;

    DataContainerI getContactsDataContainer();

    ArrayList<ContactI> getContacts();

    void addContact(ContactI contact);

    void removeContact(String username);

    ContactI getContact(String username);

    void setContacts(ArrayList<ContactI> contacts);



}
