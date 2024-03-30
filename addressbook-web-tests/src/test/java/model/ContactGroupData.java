package model;

public record ContactGroupData(String id, String groupId) {
    public ContactGroupData() {
        this("", "");
    }

    public ContactGroupData withId(String id) {
        return new ContactGroupData(id, this.groupId);
    }

    public ContactGroupData withGroupId(String groupId) {
        return new ContactGroupData(this.id, groupId);
    }
}