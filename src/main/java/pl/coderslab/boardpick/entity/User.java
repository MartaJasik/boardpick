package pl.coderslab.boardpick.entity;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.boardpick.validation.LoginValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(groups = {Default.class})
    @Size(min = 5, max = 30, message = "Must contain 5-30 characters", groups = {Default.class})
    @Column(unique = true)
    private String username;
    @NotBlank(groups = {Default.class})
    @Size(min = 5, message = "Must contain 5-30 characters", groups = {Default.class})
    private String password;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {

    }

    public User(String username, String password, int enabled, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}


