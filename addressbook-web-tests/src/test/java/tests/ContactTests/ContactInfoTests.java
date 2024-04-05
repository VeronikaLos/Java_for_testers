package tests.ContactTests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.fax())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testPhonesAddressEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContactInDB(new ContactData()
                    .withFullDataUI("Vasil", "Ivanovich", "Sidorov", "Vasya",
                            "Manager", "CompanyKo", "USA", "123", "222", "333", "444",
                            "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);

        //получить телефоны контакта с таблицы на UI
        var phones = app.contacts().getPhones(contact);

        //получить адрес контакта с таблицы на UI
        var address = app.contacts().getAddress(contact);

        //получить электрон адрес контакта с таблицы на UI
        var emails = app.contacts().getEmails(contact);

        //получить телефоны контакта с эдит формы
        var expectedPhones = app.contacts().getPhonesEditForm(contact);

        //получить телефоны контакта с эдит формы
        var expectedAddress = app.contacts().getAddressEditForm(contact);

        //получить электрон адрес контакта с эдит формы
        var expectedEmails = app.contacts().getEmailsEditForm(contact);

        Assertions.assertEquals(expectedPhones, phones);
        Assertions.assertEquals(expectedAddress, address);
        Assertions.assertEquals(expectedEmails, emails);
    }
}
