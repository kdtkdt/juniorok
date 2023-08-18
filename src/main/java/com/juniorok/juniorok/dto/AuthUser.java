package com.juniorok.juniorok.dto;

import com.juniorok.juniorok.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class AuthUser extends DefaultOAuth2User implements UserDetails {

    private final User user;

    public AuthUser(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey, User user) {
        super(authorities, attributes, nameAttributeKey);
        this.user = user;
    }

    @Override
    public <A> A getAttribute(String name) {
        return super.getAttribute(name);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

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
}
