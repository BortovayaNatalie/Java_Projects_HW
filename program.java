import java.util.*;
import phonebook.Phonebook;

public class Program {
    public static void main(String args[]) {
        Phonebook pb = new Phonebook();
        
        pb.addPhone("Test1 Test1", "+7123-456-78-90");
        System.out.println(pb.getPhoneByName("Test1 Test1"));
        System.out.println(pb.getNameByPhone("+7123-456-78-90"));
        pb.addPhone("Test2 Test2", "+7098-765-43-21");
        pb.removePhone("Test1 Test1", "+7123-456-78-90");
        pb.addPhone("Test2 Test2", "+7555-323-12-12");
        System.out.println(pb.getIdsByName("Test2 Test2"));
        System.out.println(pb.getIdsByPhone("+7098-765-43-21"));
    }
}
