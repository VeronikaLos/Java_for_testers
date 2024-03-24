package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String middleName;
    public String lastname;
    public String nickname;
    public String title;
    public String company;
    public String address;

    @Column(name = "home")
    public String homeTelephone = "";
    @Column(name = "mobile")
    public String mobileTelephone = "";
    @Column(name = "work")
    public String workTelephone = "";
    @Column(name = "fax")
    public String faxTelephone = "";
    public String email = "";
    public String email2 = "";
    public String email3 = "";
    public String homepage = "";

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String middleName, String lastname, String nickname,
                         String title, String company, String address, String homeTelephone, String mobileTelephone, String workTelephone, String faxTelephone,
                         String email, String email2, String email3, String homepage) {
        this.id = id;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homeTelephone = homeTelephone;
        this.mobileTelephone = mobileTelephone;
        this.workTelephone = workTelephone;
        this.faxTelephone = faxTelephone;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
    }
}
