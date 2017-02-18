package coms.datababys.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Lovme on 2017/2/16.
 */
@Entity
@Table(name="t_user")
public class UserEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String username;

    private String password;

    public UserEntity() {
    }

    public UserEntity(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
