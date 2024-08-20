package ru.gladyshev.springcourse.MyProjectPlusJwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 5, max = 25, message = "имя пользователя должно быть от 5 до 25 символов")
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$")
    @Column(name = "telephone")
    private String telephone;

    @NotBlank
    @Email
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @Column(name = "account_status")
    @JsonIgnore
    private boolean accountStatus;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "carOwner",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Car> car;

    public User() {
    }

    public User(String username, String telephone,String email, String password) {
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }
}
