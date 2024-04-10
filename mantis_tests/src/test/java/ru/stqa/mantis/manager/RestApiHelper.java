package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import ru.stqa.mantis.model.UserData;

public class RestApiHelper extends HelperBase{
    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));

    }

    public void createUser(UserData userData) {
        User user = new User();
        user.setUsername(userData.username());
        user.setRealName(userData.realName());
        user.setEmail(userData.email());

        UserApi apiInstance = new UserApi();
        try {
            apiInstance.userAdd(user);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }
}
