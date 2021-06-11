package sj.entity;

import js.utils.Modifiable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data @Entity @Table(name="user")
public class User implements Serializable {
    @Id private int id;
    private String realName;
}
