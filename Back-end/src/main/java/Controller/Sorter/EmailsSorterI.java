package Controller.Sorter;

import Controller.Profile.Elements.Email.EmailI;

import java.util.ArrayList;

public interface EmailsSorterI {
    ArrayList<EmailI> sort(ArrayList<EmailI> emails, String target) throws Exception;

}
