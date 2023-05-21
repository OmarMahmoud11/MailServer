package Controller.ServerControllers;

import Controller.EmailsFilter.EmailsCriteriaI;
import Controller.EmailsFilter.EmailsFilteringCustomizedCriteria;
import Controller.EmailsFilter.EmailsSearchingCustomizedCriteria;
import Controller.Profile.Elements.Email.Email;
import Controller.Profile.Elements.Email.EmailI;
import Controller.SingletonClasses.Database;
import Controller.SingletonClasses.Handlers.FirstHandler;
import Controller.Sorter.EmailsSorter;
import Controller.Sorter.EmailsSorterI;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SentPageController {
    @PostMapping("/movetoTrashSent")
    ArrayList<EmailI> movetoTrash(@RequestBody Email email){
        try{
            FirstHandler.getInstance().handle("MovetoTrash",email, "");
            return Database.getInstance().getProfilebyUsername("", email.getOwner()).getSent().getEmails();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/getSent")
    ArrayList<EmailI> getSent(@RequestParam(value = "username") String username){
        try{
            EmailsSorterI emailsSorter = new EmailsSorter(false);
            return emailsSorter.sort(Database.getInstance().getProfilebyUsername("", username).getSent().getEmails(), "date");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/sortSent")
    ArrayList<EmailI> sortSent(@RequestParam(value = "username") String username, @RequestParam(value = "target") String target, @RequestParam(value = "ascending") String ascending){
        try{
            Database database = Database.getInstance();
            EmailsSorterI sorter = new EmailsSorter(Boolean.parseBoolean(ascending));
            return sorter.sort(database.getProfilebyUsername("", username).getSent().getEmails(), target);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping("/filterSent")
    ArrayList<EmailI> filterSent(@RequestParam(value = "username") String username, @RequestParam(value = "target") String target, @RequestParam(value = "feature") String feature){
        try{
            Database database = Database.getInstance();
            EmailsCriteriaI filter = new EmailsFilteringCustomizedCriteria(feature, target);
            return filter.meetCriteria(database.getProfilebyUsername("", username).getSent().getEmails());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping("/searchSent")
    ArrayList<EmailI> searchSent(@RequestParam(value = "username") String username, @RequestParam(value = "target") String target){
        try{
            Database database = Database.getInstance();
            EmailsCriteriaI searcher = new EmailsSearchingCustomizedCriteria(target);
            return searcher.meetCriteria(database.getProfilebyUsername("", username).getSent().getEmails());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
