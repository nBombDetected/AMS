package js.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data @Table(name="role") @Entity
public class Role {
    @Id @Column(name="id") @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name") private String name;

    //配置角色的权限
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="role_authority",
            joinColumns={@JoinColumn(name="role_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="authority_id", referencedColumnName="id")})
    private Set<Authority> authorities;
}
