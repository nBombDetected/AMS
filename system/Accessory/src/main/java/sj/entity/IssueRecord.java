package sj.entity;

import js.utils.Modifiable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity @Data @Table(name = "issue_record") @Slf4j
public class IssueRecord implements Serializable, Modifiable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id") private int autoId;
    @Column(name = "id") private int id = -1;
    @Column(name = "item_id") private long itemId;
    @Column(name = "issue_amount") private int issueAmount;
    @Column(name = "claimer") private String claimer;
    @Column(name = "comment") private String comment;
    @Column(name = "reclaimed_amount") private int reclaimedAmount;
    @Column(name = "finished") private boolean finished;
    @Column(name = "valid") private boolean valid = true;
    @Column(name = "modifier_id") private int modifierId;
    @Column(name = "modify_timestamp") private Date modifyTimestamp;
    @Column(name = "reclaim_timestamp") private Date reclaimTimestamp = new Date(0);
    @Column(name = "accessory_valid") private boolean accessoryValid = true;

    @ManyToOne(targetEntity = Accessory.class, cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "item_id", referencedColumnName = "item_id", updatable = false, insertable = false),
            @JoinColumn(name = "accessory_valid", referencedColumnName = "valid",updatable = false, insertable = false)
    })
    private Accessory accessory;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "modifier_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User modifier;

    public IssueRecord() {}
    public IssueRecord(long itemId, int issueAmount, String claimer, String comment) {
        this.itemId = itemId;
        this.issueAmount = issueAmount;
        this.claimer = claimer;
        this.comment = comment;
    }

    @Override
    public void modifiedBy(int userId) {
        modifierId = userId;
        modifyTimestamp = new Date(System.currentTimeMillis());
    }

    public void setValid(boolean valid){
        this.valid = valid;
        if (valid) {
            accessory.stockout(issueAmount);
            accessory.stockin(reclaimedAmount);
        } else {
            accessory.stockin(issueAmount);
            accessory.stockout(reclaimedAmount);
            log.debug("标记无效，打印accessory信息：{}", accessory.getAmount());
            log.debug("打印issueRecord：{}", this);
        }
    }

    public void reclaim(int amount) {
        valid = true;
        reclaimedAmount += amount;
    }
}
