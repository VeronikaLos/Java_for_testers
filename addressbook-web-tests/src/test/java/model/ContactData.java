package model;

public record ContactData(String id, String firstName, String middleName, String lastName, String nickname,
                          String title, String company, String address,
                          String homeTelephone, String mobileTelephone, String workTelephone, String faxTelephone,
                          String email, String email2, String email3, String homePage) {
    public ContactData() {
        this("", "", "", "", "", "", "", "",
                "", "", "", "",
                "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.nickname, this.title, this.company, this.address,
                this.homeTelephone, this.mobileTelephone, this.workTelephone, this.faxTelephone, this.email, this.email2,
                this.email3, this.homePage);
    }

    public ContactData withName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.nickname, this.title, this.company, this.address,
                this.homeTelephone, this.mobileTelephone, this.workTelephone, this.faxTelephone, this.email, this.email2,
                this.email3, this.homePage);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.nickname, this.title, this.company, this.address,
                this.homeTelephone, this.mobileTelephone, this.workTelephone, this.faxTelephone, this.email, this.email2,
                this.email3, this.homePage);
    }

    public ContactData withFullDataUI(String firstName, String middleName, String lastName, String nickname, String title, String company, String address,
                                      String homeTelephone, String mobileTelephone, String workTelephone, String faxTelephone, String email,String email2,
                                      String email3, String homePage) {
        return new ContactData(this.id, firstName, middleName, lastName, nickname, title, company, address,
                homeTelephone, mobileTelephone, workTelephone, faxTelephone, email, email2,
                email3, homePage);
    }
}
