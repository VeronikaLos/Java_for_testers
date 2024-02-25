package model;

public record ContactData(String firstName, String middleName, String lastName, String nickname, String title,
                          String company,
                          String address, String homeTelephone, String mobileTelephone, String workTelephone,
                          String faxTelephone,
                          String email, String email2, String email3, String homePage) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withName(String name) {
        return new ContactData(name, this.middleName, this.lastName, this.nickname, this.title, this.company, this.address,
                this.homeTelephone, this.mobileTelephone, this.workTelephone, this.faxTelephone, this.email, this.email2,
                this.email3, this.homePage);
    }
}
