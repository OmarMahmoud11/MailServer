package Controller.ContactsFilter;

import Controller.Profile.Elements.Contacts.ContactI;

import java.util.ArrayList;

public interface ContactsCriteriaI {
    ArrayList<ContactI> meetCriteria(ArrayList<ContactI> contacts);
}
