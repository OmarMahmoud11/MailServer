package UnitTesting;
import Controller.EmailsFilter.EmailsSearchingCustomizedCriteria;
import Controller.Profile.Elements.Email.Email;
import Controller.Profile.Elements.Email.EmailI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class EmailsSearchingTester {
    static EmailI firstEmail = new Email("test","hello my friend","Ahmed", new ArrayList<String>(Arrays.asList(new String[]{"Muhammad"})),null,null, "skippable");
    static EmailI secondEmail = new Email("test","hello programmer","Joe", new ArrayList<String>(Arrays.asList(new String[]{"Mn3m"})),null,null, "non-essential");
    static ArrayList<EmailI> emails = new ArrayList<EmailI>();
    static EmailsSearchingCustomizedCriteria searcher = mock(EmailsSearchingCustomizedCriteria.class);

    @BeforeAll
    public static void setUp(){
        emails.add(firstEmail);
        emails.add(secondEmail);
        searcher.setTarget("programmer");
    }

    @Test
    public void test(){
        when(searcher.meetCriteria(emails)).thenReturn(new ArrayList<EmailI>(Collections.singleton(secondEmail)));
    }
}
