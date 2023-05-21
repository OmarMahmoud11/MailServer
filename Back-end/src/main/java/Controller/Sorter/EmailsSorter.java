package Controller.Sorter;

import Controller.Profile.Elements.Email.EmailI;

import java.util.*;

public class EmailsSorter implements EmailsSorterI {

    boolean ascending;

    public EmailsSorter(boolean ascending){
        this.ascending = ascending;
    }
    private static ArrayList<String> priorities = new ArrayList<>(
            Arrays.asList("urgent", "important", "standard", "skippable")
    );

    public ArrayList<EmailI> sort(ArrayList<EmailI> emails, String target) throws Exception{
        ArrayList<EmailI> sortedEmails;
        target = target.toLowerCase();
        switch (target){
            case "date":
                sortedEmails = this.sortByDate(emails); break;
            case "sender":
                sortedEmails = this.sortBySender(emails); break;
            case "receiver":
                sortedEmails = this.sortByReceiver(emails); break;
            case "subject":
                sortedEmails = this.sortBySubject(emails); break;
            case "body":
                sortedEmails = this.sortByBody(emails); break;
            case "attacments":
                sortedEmails = this.sortByAttachments(emails); break;
            case "priority":
                sortedEmails = this.sortByPriority(emails); break;
            default: throw new Exception("NO SUCH CRITERIA TO SORT");
        }
        return sortedEmails;

    }

    private ArrayList<EmailI> sortBySender(ArrayList<EmailI> emails) {

        EmailI[] emailsSorted = emails.toArray(EmailI[]::new);
        Arrays.sort(emailsSorted, Comparator.comparing(email -> email.getSenderUsername().toLowerCase()));
        ArrayList<EmailI> ret =  new ArrayList<>(Arrays.asList(emailsSorted));
        if (!this.ascending) {
            Collections.reverse(ret);
        }
        return ret;
    }

    private ArrayList<EmailI> sortByReceiver(ArrayList<EmailI> emails) {
        EmailI[] emailsSorted = emails.toArray(EmailI[]::new);
        Arrays.sort(emailsSorted, Comparator.comparing(email -> email.getReceiversUsernames().get(0).toLowerCase()));
        ArrayList<EmailI> ret =  new ArrayList<>(Arrays.asList(emailsSorted));
        if (!this.ascending) {
            Collections.reverse(ret);
        }
        return ret;
    }

    private ArrayList<EmailI> sortByDate(ArrayList<EmailI> emails) {
        EmailI[] emailsSorted = emails.toArray(EmailI[]::new);
        Arrays.sort(emailsSorted, (first, second) -> {
           if(Long.parseLong(first.getTimeSent()) >= Long.parseLong(second.getTimeSent())){
               return 1;
           }
           else{
               return -1;
           }
        });
        ArrayList<EmailI> ret =  new ArrayList<>(Arrays.asList(emailsSorted));
        if (!this.ascending) {
            Collections.reverse(ret);
        }
        return ret;
    }

    private ArrayList<EmailI> sortByAttachments(ArrayList<EmailI> emails) {
        EmailI[] emailsSorted = emails.toArray(EmailI[]::new);
        Arrays.sort(emailsSorted, Comparator.comparing(email -> email.getAttachments().size()));
        ArrayList<EmailI> ret =  new ArrayList<>(Arrays.asList(emailsSorted));
        if (!this.ascending) {
            Collections.reverse(ret);
        }
        return ret;
    }

    private ArrayList<EmailI> sortByBody(ArrayList<EmailI> emails) {
        EmailI[] emailsSorted = emails.toArray(EmailI[]::new);
        Arrays.sort(emailsSorted, Comparator.comparing(email -> email.getBody().toLowerCase()));
        ArrayList<EmailI> ret =  new ArrayList<>(Arrays.asList(emailsSorted));
        if (!this.ascending) {
            Collections.reverse(ret);
        }
        return ret;
    }

    private ArrayList<EmailI> sortBySubject(ArrayList<EmailI> emails) {
        EmailI[] emailsSorted = emails.toArray(EmailI[]::new);
        Arrays.sort(emailsSorted, Comparator.comparing(email -> email.getSubject().toLowerCase()));
        ArrayList<EmailI> ret =  new ArrayList<>(Arrays.asList(emailsSorted));
        if (!this.ascending) {
            Collections.reverse(ret);
        }
        return ret;
    }

    private ArrayList<EmailI> sortByPriority(ArrayList<EmailI> emails){

        EmailI[] emailsSorted = emails.toArray(EmailI[]::new);
        Arrays.sort(emailsSorted, (first, second) -> {
            if(priorities.indexOf(first.getPriority().toLowerCase()) < priorities.indexOf(second.getPriority().toLowerCase())){
                return 1;
            }
            else{
                return -1;
            }
        });
        ArrayList<EmailI> ret =  new ArrayList<>(Arrays.asList(emailsSorted));
        if (!this.ascending) {
            Collections.reverse(ret);
        }
        return ret;

    }
}
