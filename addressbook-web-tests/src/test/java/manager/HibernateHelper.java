package manager;

import manager.hbm.ContactGroupRecord;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.ContactGroupData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactGroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    //ContactGroupData
    public List<ContactGroupData> getContactGroupList() {
        return convertContactGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactGroupRecord", ContactGroupRecord.class).list();
        }));
    }

    static List <ContactGroupData> convertContactGroupList(List<ContactGroupRecord> records) {
        List<ContactGroupData> result = new ArrayList<>();
        for (var record : records ) {
            result.add(convert(record));
        }
        return result;
    }

    private static ContactGroupData convert(ContactGroupRecord record) {
        //return new ContactGroupData("" + record.id, "" + record.groupId);
        return new ContactGroupData().withId("" + record.id).withGroupId("" + record.groupId);
    }

    private static ContactGroupRecord convert(ContactGroupData data) {
        var id = data.id();
        if("".equals(id)) {
            id = "0";
        }
        return new ContactGroupRecord(Integer.parseInt(data.id()), Integer.parseInt(data.groupId()));
    }

    public long getContactGroupRecordCount() {
        return (sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactGroupRecord", long.class).getSingleResult();
        }));
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData(String.valueOf(record.id), record.name, record.header, record.footer);
    }

    public long getGroupCount() {
        return (sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", long.class).getSingleResult();
        }));
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }
    public long getContactCount() {
        return (sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", long.class).getSingleResult();
        }));
    }

    public void createContactInDB(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData().withId("" + record.id)
                .withFullDataUI(record.firstname, record.middleName, record.lastname, record.nickname, record.title, record.company, record.address,
                        record.homeTelephone, record.mobileTelephone, record.workTelephone, record.faxTelephone, record.email, record.email2, record.email3, record.homepage);
    }
    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.middleName(), data.lastName(), data.nickname(),
                data.title(), data.company(), data.address(), data.homeTelephone(), data.mobileTelephone(), data.workTelephone(), data.faxTelephone(),
                data.email(), data.email2(), data.email3(), data.homePage());
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
