package tests.ContactTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
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

    @Test
    public void canCreateContactWithNameOnly() {
        var oldContacts = app.contacts().getContactList();
        app.contacts().createContact(new ContactData().withName("Ivan"));
        var newContacts = app.contacts().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(new ContactData()
                .withId(newContacts.get(newContacts.size() - 1).id())
                .withName("Ivan"));
        expectedList.sort(compareById);
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
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(new ContactData()
                .withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateContact() {
        var oldContacts = app.contacts().getContactList();
        app.contacts().createContact(new ContactData(
                "", "Vasil", "Ivanovich", "Sidorov", "Vasya", "Manager", "CompanyKo", "USA",
                "555", "777", "888", "999",
                "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
        var newContacts = app.contacts().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(new ContactData()
                .withId(newContacts.get(newContacts.size() - 1).id())
                .withName("Vasil")
                .withLastName("Sidorov"));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
