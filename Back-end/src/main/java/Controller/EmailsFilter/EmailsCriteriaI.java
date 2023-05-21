package Controller.EmailsFilter;

import Controller.Profile.Elements.Email.EmailI;

import java.util.ArrayList;

public interface EmailsCriteriaI {
    ArrayList<EmailI> meetCriteria(ArrayList<EmailI> emails);
}
