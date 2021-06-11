package js.entity;

import js.utils.JWTUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data @Entity @Table(name="user")
public class User {
    @Id
    private int id;
    private String name;
    private String password;
    private String realName;
    @Transient
    private String token;
    //配置用户的角色
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Set<Role> roles;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public User() { }

    public void setToken(){
        Map<String, String> payload = new HashMap<>();
        payload.put("id", String.valueOf(id));
        payload.put("username", this.name);
        this.token = JWTUtils.getToken(payload);
    }

    /** @param resource 具体资源地址 */
    public boolean isAuthorizedTo(String resource) {
        for(Role role: roles){
            for(Authority authority: role.getAuthorities()){
                if(resource.matches("^"+authority.getResource()+"$")) return true;
            }
        }
        return false;
    }
}
