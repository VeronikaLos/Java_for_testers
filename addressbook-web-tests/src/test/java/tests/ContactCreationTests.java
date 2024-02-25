package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

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
        app.contacts().createContact(new ContactData("Vasil", "Ivanovich", "Sidorov", "Vasya",
                "Manager", "CompanyKo", "USA", "555", "777", "888",
                "999", "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
    }
}
