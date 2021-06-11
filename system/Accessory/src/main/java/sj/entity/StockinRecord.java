package sj.entity;

import js.utils.Modifiable;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data @Entity @Table(name = "stockin_record")
public class StockinRecord implements Serializable, Modifiable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id") private int autoId;
    @Column(name = "id") private int id = -1;
    @Column(name = "item_id") private long itemId;
    @Column(name = "amount") private int amount;
    @Column(name = "supplier_id") private int supplierId;
    @Column(name = "comment") private String comment;
    @Column(name = "modifier_id") private int modifierId;
    @Column(name = "modify_timestamp") private Date modifyTimestamp;
    @Column(name = "valid") private boolean valid = true;
    @Column(name = "accessory_valid") private boolean accessoryValid = true;

    @ManyToOne(targetEntity = Accessory.class, cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "item_id", referencedColumnName = "item_id", updatable = false, insertable = false),
            @JoinColumn(name = "accessory_valid", referencedColumnName = "valid", updatable = false, insertable = false)
    })
    private Accessory accessory;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "modifier_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User modifier;

    public StockinRecord() {}
    public StockinRecord(int id, long itemId, int amount, int supplierId, String comment) {
        this.id = id;
        this.itemId = itemId;
        this.amount = amount;
        this.supplierId = supplierId;
        this.comment = comment;
    }

    @Override
    public void modifiedBy(int userId) {
        modifierId = userId;
        modifyTimestamp = new Date(System.currentTimeMillis());
    }

    public void setValid(boolean valid) {
        this.valid = valid;
        if(valid) {
            accessory.stockin(amount);
        } else {
            accessory.stockout(amount);
        }
    }
}
