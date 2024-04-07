package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.Common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {


    @Test
    void canRegisterUser() {
        var userName = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", userName);

        //создать новый имейл на почтовом сервере
        app.jamesCli().addUser(email, "password");

        //залогиниться в мантис как админ
        app.session().login("administrator", "root");

        //создать новый акаунт
        app.mantis().createNewAccount(userName, email);

        //вылогиниться
        app.session().logout();

        //получаем почту, берем линку и переходим по ней
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            app.driver().get(url);
        }

        //Сменить пароль
        app.mantis().changePasswordAfterRegistration();

        //залогиниться под новым юзером
        app.http().login(userName, "password");

        //проверка
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
