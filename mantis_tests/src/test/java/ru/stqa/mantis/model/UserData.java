package ru.stqa.mantis.model;

public record UserData(String username, String realName, String email) {

    public UserData() {
        this("", "", "");
    }

    public UserData withUsername(String username) {
        return new UserData(username, this.realName, this.email);
    }

    public UserData withRealname(String realName) {
        return new UserData(this.username, realName, this.email);
    }

    public UserData withEmail(String email) {
        return new UserData(this.username, this.realName, email);
    }
}
