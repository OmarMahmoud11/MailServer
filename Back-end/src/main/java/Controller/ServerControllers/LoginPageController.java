package Controller.ServerControllers;

import Controller.Profile.ProfileI;
import Controller.SingletonClasses.Database;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginPageController {
    @PostMapping("/createProfile")
    String createProfile(@RequestBody String encryption){
        String ret;
        try{
            Database database =  Database.getInstance();
            database.addProfile(encryption);
            database.printDatabase();
            ret = "CREATED PROFILE SUCCESSFULLY";
        }
        catch (Exception e){
            System.out.println(e);
            ret = e.getMessage();
        }
        return ret;
    }
    @GetMapping("/getProfile")
    ProfileI getProfile(@RequestParam(value="encryption") String encryption){
        try{
            Database database = Database.getInstance();
            return database.getProfilebyEncryption(encryption);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
