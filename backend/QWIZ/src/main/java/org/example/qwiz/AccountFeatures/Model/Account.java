package org.example.qwiz.AccountFeatures.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.qwiz.ClassroomFeatures.Model.Classroom;
import org.example.qwiz.Security.Model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    @Column(nullable = false,name = "username")
    private String username;
    @Column(nullable = false,name = "email")
    public String email;
    @Column(nullable = false, name = "password")
    public String password;
    @Enumerated(EnumType.STRING)
    public Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
    @ManyToMany(mappedBy = "account",fetch = FetchType.LAZY)
    public List<Classroom> classrooms = new ArrayList<>();

    public Account(Integer id) {
        this.id = id;
    }
}
