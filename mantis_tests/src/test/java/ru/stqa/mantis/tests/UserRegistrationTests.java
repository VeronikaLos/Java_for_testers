package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.Common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUserViaCli() {
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

        var url = CommonFunctions.extractUrl(messages.get(0).content());
        //app.driver().get(url);

        //Сменить пароль
        app.mantis().changePasswordAfterRegistration(url);

        //залогиниться под новым юзером
        app.http().login(userName, "password");

        //проверка
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegisterUserViaApi() {
        var userName = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", userName);

        //1. создать новый имейл на почтовом сервере James через удаленный программный интерфейс REST API
        app.JamesApi().addUser(email, "password");

        // 2. Сценарий начинает регистрацию нового пользователя в Mantis, используя REST API.
        app.rest().createUser(new UserData()
                .withUsername(userName)
                .withRealname(userName + CommonFunctions.randomString(5))
                .withEmail(email));

        //3. получаем почту, берем линку и переходим по ней
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        //Сменить пароль
        app.mantis().changePasswordAfterRegistration(url);
        //4. залогиниться под новым юзером
        app.http().login(userName, "password");
        //проверка
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
