package UnitTesting;
import Controller.EmailsFilter.EmailsFilteringCustomizedCriteria;
import Controller.Profile.Elements.Email.Email;
import Controller.Profile.Elements.Email.EmailI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class EmailsFilterTester {
    static EmailI firstEmail = new Email("test","hello programmer","Ahmed", new ArrayList<String>(Arrays.asList(new String[]{"Muhammad"})),null,null,"crucial");
    static EmailI secondEmail = new Email("test","hello programmer","Joe", new ArrayList<String>(Arrays.asList(new String[]{"Mn3m"})),null,null, "important");
    static ArrayList<EmailI> emails = new ArrayList<EmailI>();
    static EmailsFilteringCustomizedCriteria filter = mock(EmailsFilteringCustomizedCriteria.class);

    @BeforeAll
    public static void setUp(){
        emails.add(firstEmail);
        emails.add(secondEmail);
        filter.setFeature("senderUsername");
        filter.setTarget("Ahmed");
    }

    @Test
    public void test(){
        when(filter.meetCriteria(emails)).thenReturn(new ArrayList<EmailI>(Collections.singleton(firstEmail)));
    }
}
