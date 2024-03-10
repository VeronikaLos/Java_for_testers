package tests.GroupsTests;

import model.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup () {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "", "", ""));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
