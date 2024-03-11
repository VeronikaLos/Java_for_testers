package tests.GroupsTests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
    }

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();

        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new GroupData().
                    withName(randomString(i * 5)).
                    withHeader(randomString(i * 5)).
                    withFooter(randomString(i * 5)));
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);

        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }
}
