package js.entity;

import lombok.Data;

import javax.persistence.*;

@Entity @Data @Table(name="authority")
public class Authority {
    @Id @Column(name="id") @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name") private String name;
    @Column(name="resource") private String resource;
}
