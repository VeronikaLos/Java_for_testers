package tests.ContactTests;

import Common.CommonFunctions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactData;
import model.GroupData;
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
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact
                .withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateContactWithNameOnly() {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(new ContactData().withName("Ivan"));
        var newContacts = app.hbm().getContactList();
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
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(new ContactData());
        var newContacts = app.hbm().getContactList();
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
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(new ContactData()
                .withFullDataUI("Vasil", "Ivanovich", "Sidorov", "Vasya",
                        "Manager", "CompanyKo", "USA", "123", "222", "333", "444",
                        "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(new ContactData()
                .withId(newContacts.get(newContacts.size() - 1).id())
                .withFullDataUI("Vasil", "Ivanovich", "Sidorov", "Vasya",
                        "Manager", "CompanyKo", "USA", "123", "222", "333", "444",
                        "Ivan@Gmail.com", "Ivan2@Gmail.com", "Ivan3@Gmail.com", "Page"));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateContactInGroup() {
        //создай новый контакт
        var contact = new ContactData()
                .withName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(5));
        //Если группы нет, то создай новую
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        //возьми первую группу в списке
        var group = app.hbm().getGroupList().get(0);

        //Получить список контактов в заданной группе
        var oldRelated = app.hbm().getContactsInGroup(group);

        //Создать контакт в заданную группу
        app.contacts().createContact(contact, group);

        //Получить Новый список контактов в заданной группе
        var newRelated = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact
                .withId(newRelated.get(newRelated.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }

    @Test
    public void canAddContactInGroup() {
        //Получить список всех контактов
        var contacts = app.hbm().getContactList();

        //взять первую группу в списке
        var group = app.hbm().getGroupList().get(0);

        //получить список контактов в этой группе
        var contactsInGroup = app.hbm().getContactsInGroup(group);

        //найти/создать контакт который не в этой группе
        var contact = app.contacts().defineContact(contacts, contactsInGroup).get(0);

        //добавить контакт в группу
        app.contacts().addContactInGroup(contact, group);

        var newContactsInGroup = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactsInGroup.sort(compareById);
        var expectedList = new ArrayList<>(contactsInGroup);
        expectedList.add(contact.withId(contact.id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContactsInGroup, expectedList);
    }

    @Test
    public void canRemoveContactFromGroup() {
        //если нет ни одной записи в ContactGroupRecord, то создай новую группу и создай новый контакт в группу.
        if (app.hbm().getContactGroupRecordCount() == 0) {
            var group = new GroupData("", "group name", "group header", "group footer");
            app.hbm().createGroup(group);
            var contact = new ContactData()
                    .withName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10));
            app.contacts().createContact(contact, group);
        }
        //взять первую контакт-группу из списка
        var contactGroupForRemove = app.jdbc().getContactGroupList().get(0);
        var group = new GroupData().withId(contactGroupForRemove.groupId());
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contactGroupForRemove);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        List<ContactData> expectedList = new ArrayList<>(oldRelated);
        var expectedListNew = app.contacts().removeContactFromList(expectedList,contactGroupForRemove);
        expectedListNew.sort(compareById);
        Assertions.assertEquals(newRelated, expectedListNew);
        Assertions.assertEquals(oldRelated.size() -1, newRelated.size());
    }
}
