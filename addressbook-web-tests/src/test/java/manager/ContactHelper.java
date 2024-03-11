package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        submitContactCreation();
        returnHomePage();
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedGroup();
        openHomePage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnHomePage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", contact.id())));
    }

    private void returnHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homeTelephone());
        type(By.name("mobile"), contact.mobileTelephone());
        type(By.name("work"), contact.workTelephone());
        type(By.name("fax"), contact.faxTelephone());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("homepage"), contact.homePage());
    }

    private void openAddNewPage() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void openHomePage() {
        click(By.linkText("home"));
    }


    private void removeSelectedGroup() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var tds = manager.driver.findElements(By.name("entry"));
        for (var td : tds) {
            var checkbox = td.findElement(By.className("center")).findElement(By.name("selected[]"));
            var name = td.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastName = td.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName));
        }
        return contacts;
    }
}