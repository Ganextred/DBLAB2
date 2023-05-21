package com.example.dblab2.data.dto;




import java.util.Set;


public class User{

    private Long id;
    private String username;

    private String email;
    private boolean active = true;
    String password;


    private Set<Role> roles;


    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(Long id, boolean active, String email,String password, String username) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.active = active;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {this.id = id; return this;}

    public String getUsername() {
        return username;
    }


    public User setUsername(String name) {this.username = name; return this;}

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {this.email = email; return this;}

    public boolean isActive() {
        return active;
    }

    public User setActive(boolean active) {this.active = active; return  this;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {this.roles = roles; return this;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public boolean hasRole (String roleStr){
        Role role;
        try {
            role = Role.valueOf(roleStr);
        }catch (IllegalArgumentException e){
            return false;
        }
        return roles.contains(role);
    }

}