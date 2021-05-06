package com.library.management.security;


import com.library.management.model.User;
import com.library.management.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@AllArgsConstructor
@ToString
public class CustomUserDetails implements UserDetails {


    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> roleList = new ArrayList<>();
        roleList.add(user.getUserRole());
        return getAuthorities(roleList);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() { return user.getUsername(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));

//            role.getRights().stream()
//                    .map(r -> new SimpleGrantedAuthority(r.getRightName()))
//                    .forEach(authorities::add);
        }

        return authorities;
    }


}
