package Controller.Sorter;

import Controller.Profile.Elements.Contacts.Contact;
import Controller.Profile.Elements.Contacts.ContactI;

import java.util.ArrayList;

public interface ContactsSorterI {
    ArrayList<ContactI> sort(ArrayList<ContactI> contacts);

}
