package pl.coderslab.springsecurity.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserName {
    @Id
    private String username;
    private String password;
    private boolean enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserName() {
        this.setEnabled(true);
    }
}
