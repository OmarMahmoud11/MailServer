package Controller.ServerControllers;


import Controller.ContactsFilter.ContactsSearchingCustomizedCriteria;
import Controller.Profile.Elements.Contacts.Contact;
import Controller.Profile.Elements.Contacts.ContactI;
import Controller.SingletonClasses.Creator;
import Controller.SingletonClasses.Database;
import Controller.SingletonClasses.Deleter;
import Controller.Sorter.ContactsSorter;
import Controller.Sorter.ContactsSorterI;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContactsPageController {

    @PostMapping("/addContact")
    String addContact(@RequestParam(value = "username") String username, @RequestBody Contact contact){
        try {
            Database database = Database.getInstance();
            ContactI newContact = new Contact();
            newContact.setEmailAddresses(contact.getEmailAddresses());
            newContact.setUsername(contact.getUsername());
            Creator.getInstance().createContactData(database.getProfilebyUsername("", username), newContact);
            database.getProfilebyUsername("",username).getContacts().addContact(newContact);
            return "CREATED CONTACT SUCCESSFULLY";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GetMapping ("/getContacts")
    ArrayList<ContactI> getContacts(@RequestParam(value = "username") String username){
        try {
            Database database = Database.getInstance();
            return database.getProfilebyUsername("", username).getContacts().getContacts();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*@PostMapping("/editContact")
    void editContact(@RequestParam(value = "username") String username,@RequestParam(value = "oldContact") ContactI oldContact, @RequestParam(value = "newContact") ContactI newContact){
        try {
            Database database = Database.getInstance();
            Deleter.getInstance().deleteContact(oldContact, database.getProfilebyUsername("", username));
            Creator.getInstance().createContactData(database.getProfilebyUsername("", username), newContact);
          //  return ;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }*/

    @DeleteMapping("/removeContact")
    String removeContact(@RequestParam(value = "contactusername") String contactUsername, @RequestParam(value = "username") String username){
        try {
            Database database = Database.getInstance();
            Deleter.getInstance().deleteContact(database.getProfilebyUsername("", username).getContacts().getContact(contactUsername), database.getProfilebyUsername("", username));
            database.getProfilebyUsername("",username).getContacts().removeContact(contactUsername);
            return "DELETED CONTACT SUCCESSFULLY";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GetMapping("/sortContacts")
    ArrayList<ContactI> sortContacts(@RequestParam(value = "username") String username, @RequestParam(value = "ascending") String ascending) {
        try {
            ContactsSorterI sorter = new ContactsSorter(Boolean.parseBoolean(ascending));
            return sorter.sort(Database.getInstance().getProfilebyUsername("", username).getContacts().getContacts());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/searchContacts")
    ArrayList<ContactI> searchContacts(@RequestParam(value = "username") String username,@RequestParam(value = "target") String target){
        try {
            Database database = Database.getInstance();
            ContactsSearchingCustomizedCriteria search = new ContactsSearchingCustomizedCriteria(target);
            return search.meetCriteria(database.getProfilebyUsername("",username).getContacts().getContacts());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
