package ru.gladyshev.springcourse.MyProjectPlusJwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.login.AccountLockedException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
        private static final long serialVersionUID = 1L;

        private Integer id;

        private String username;

        private String email;

        @JsonIgnore
        private String password;

        @JsonIgnore
        private boolean accountStatus;

        private Collection<? extends GrantedAuthority> authorities;

        public UserDetailsImpl(Integer id, String username, String email, String password,boolean accountStatus,
                               Collection<? extends GrantedAuthority> authorities) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
            this.accountStatus = accountStatus;
            this.authorities = authorities;
        }
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.isAccountStatus(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Integer getId() {
            return id;
        }
        public String getEmail() {
            return email;
        }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return isAccountStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            UserDetailsImpl user = (UserDetailsImpl) o;
            return Objects.equals(id, user.id);
        }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, authorities);
    }
}
