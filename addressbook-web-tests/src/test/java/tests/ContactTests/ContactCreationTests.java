package tests.ContactTests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithNameOnly() {
        app.contacts().createContact(new ContactData().withName("Ivan"));
    }

    @Test
    public void canCreateContactWithEmptyName() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData(
                "Vasil", "Ivanovich", "Sidorov", "Vasya", "Manager", "CompanyKo", "USA",
                "555", "777", "888", "999",
                "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
    }

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10),
                    randomString(i * 10), randomString(i * 10), randomString(i * 10),
                    randomPhone(i * 2), randomPhone(i * 2), randomPhone(i * 2), randomPhone(i * 2),
                    randomEmails(i * 5), randomEmails(i * 5), randomEmails(i * 5), randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
