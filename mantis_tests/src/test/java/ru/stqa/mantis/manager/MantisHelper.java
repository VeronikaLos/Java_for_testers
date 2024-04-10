package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class MantisHelper extends HelperBase {
    public MantisHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createNewAccount(String username, String email) {
        click((By.xpath("//*[text()=' Manage ']")));
        click((By.xpath("//*[text()='Users']")));
        click((By.xpath("//*[text()='Create New Account']")));
        type(By.id("user-username"), username);
        type(By.id("user-realname"), username);
        type(By.id("email-field"), email);
        click((By.xpath("//input[@value='Create User']")));
    }

    public void changePasswordAfterRegistration(String url) {
        manager.driver().get(url);
        type(By.id("password"), "password");
        type(By.id("password-confirm"), "password");
        click((By.xpath("//*[text()='Update User']")));
    }
}
