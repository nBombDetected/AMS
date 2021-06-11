package sj.entity;

import lombok.Data;

import javax.persistence.*;

@Data @Entity @Table(name = "supplier")
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private long id;
    @Column(name = "name") private String name;
    @Column(name = "phone") private long phone;
}
