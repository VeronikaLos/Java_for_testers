package tests.ContactTests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContactInDB(new ContactData()
                    .withFullDataUI("Vasil", "Ivanovich", "Sidorov", "Vasya",
                            "Manager", "CompanyKo", "USA", "123", "222", "333", "444",
                            "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);

        Assertions.assertEquals(newContacts, expectedList);
    }
}
