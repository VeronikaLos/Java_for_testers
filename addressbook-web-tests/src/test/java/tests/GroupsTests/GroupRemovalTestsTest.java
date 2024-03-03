package tests.GroupsTests;

import model.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupRemovalTestsTest extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "", ""));
        }
        app.groups().removeGroup();
    }
}
