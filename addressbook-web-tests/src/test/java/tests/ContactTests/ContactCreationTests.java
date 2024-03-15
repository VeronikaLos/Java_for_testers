package tests.ContactTests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithNameOnly() {
        var oldContacts = app.contacts().getContactList();
        app.contacts().createContact(new ContactData().withName("Ivan"));
        var newContacts = app.contacts().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        System.out.println(newContacts);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(new ContactData()
                .withId(newContacts.get(newContacts.size() - 1).id())
                .withName("Ivan"));
        expectedList.sort(compareById);
        System.out.println(expectedList);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateContactWithEmptyName() {
        var oldContacts = app.contacts().getContactList();
        app.contacts().createContact(new ContactData());
        var newContacts = app.contacts().getContactList();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        System.out.println(newContacts);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(new ContactData()
                .withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        System.out.println(expectedList);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData(
                "", "Vasil", "Ivanovich", "Sidorov", "Vasya", "Manager", "CompanyKo", "USA",
                "555", "777", "888", "999",
                "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
    }

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData().
                    withName(randomString(i * 5)).
                    withLastName(randomString(i * 5)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact
                .withId(newContacts.get(newContacts.size() - 1).id()));
//                .withName(contact.firstName())
//                .withLastName(contact.lastName()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
