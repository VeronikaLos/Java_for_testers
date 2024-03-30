package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address_in_groups")
public class ContactGroupRecord {

    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "group_id")
    public int groupId;

    public ContactGroupRecord() {}

    public ContactGroupRecord(int id, int groupId) {
        this.id = id;
        this.groupId = groupId;
    }
}
