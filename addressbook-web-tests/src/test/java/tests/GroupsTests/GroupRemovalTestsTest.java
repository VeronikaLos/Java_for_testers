package tests.GroupsTests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTestsTest extends TestBase {

//    @Test
//    public void canRemoveGroup() {
//        if (!app.groups().isGroupPresent()) {
//            app.groups().createGroup(new GroupData("", "", ""));
//        }
//        app.groups().removeGroup();
//    }
    @Test
    void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }

        var oldGroups = app.groups().getGroupList();

        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());

        app.groups().removeGroup(oldGroups.get(index));

        var newGroups = app.groups().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}