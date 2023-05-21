package Controller.Sorter;

import Controller.Profile.Elements.Contacts.ContactI;

import java.util.*;

public class ContactsSorter implements ContactsSorterI {
    boolean ascending;
    public ContactsSorter(boolean ascending){
        this.ascending = ascending;
    }
    @Override
    public ArrayList<ContactI> sort(ArrayList<ContactI> contacts) {
        ContactI[] contactsSorted = contacts.toArray(ContactI[]::new);
        Arrays.sort(contactsSorted, Comparator.comparing(contactI -> contactI.getUsername().toLowerCase()));
        ArrayList<ContactI> ret = new ArrayList<>(Arrays.asList(contactsSorted));
        if(!this.ascending){
            Collections.reverse(ret);
        }
        return ret;
    }
}
