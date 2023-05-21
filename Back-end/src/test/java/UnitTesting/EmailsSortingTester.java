package UnitTesting;
import Controller.Profile.Elements.Email.Email;
import Controller.Profile.Elements.Email.EmailI;
import Controller.Sorter.EmailsSorter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Arrays;

public class EmailsSortingTester {

    static EmailI firstEmail = new Email("test","hello programmer","joe", new ArrayList<String>(Arrays.asList(new String[]{"Muhammad"})),null,null, "non-nessential");
    static EmailI secondEmail = new Email("test","hello programmer","ahmed", new ArrayList<String>(Arrays.asList(new String[]{"Mn3m"})),null,null, "crucial");
    static ArrayList<EmailI> enteredEmails = new ArrayList<EmailI>();
    static ArrayList<EmailI> sortedEmails = new ArrayList<EmailI>();
    static EmailsSorter sorter = mock(EmailsSorter.class);

    @BeforeAll
    public static void setUp(){
        enteredEmails.add(firstEmail);
        enteredEmails.add(secondEmail);
        sortedEmails.add(secondEmail);
        sortedEmails.add(firstEmail);

    }

    @Test
    public void test() throws Exception {
        when(sorter.sort(enteredEmails,"senderUsername")).thenReturn(sortedEmails);
    }
}
