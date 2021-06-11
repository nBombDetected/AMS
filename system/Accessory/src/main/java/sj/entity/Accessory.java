package sj.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import js.utils.Modifiable;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data @Entity @Table(name = "accessory")
public class Accessory implements Serializable, Modifiable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private int id;
    @Column(name = "item_id") private long itemId;
    @Column(name = "name") private String name;
    @Column(name = "unit_of_measurement") private String unitOfMeasurement;
    @Column(name = "amount") private int amount;
    @Column(name = "modifier_id") private int modifierId;
    @Column(name = "modify_timestamp") private Date modifyTimestamp;
    @Column(name = "valid")
    @Type(type = "boolean")
    private Boolean valid;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "modifier_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User modifier;

    public void modifiedBy(int userId) {
        modifierId = userId;
        modifyTimestamp = new Date(System.currentTimeMillis());
    }

    public void stockout(int amount) {
        this.amount -= amount;
    }

    public void stockin(int amount) {
        this.amount += amount;
    }
}
