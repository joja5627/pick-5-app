package io.pick5.domain;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private String username;
    private String password;
    private String email;
    private String confirmationCode;
    
    @Builder.Default()
    private final String id = UUID.randomUUID().toString();
    

    @Builder.Default()
    private boolean active = false;

    @Builder.Default()
    private List<String> roles = List.of();


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()]));
    }


    public boolean isAccountNonExpired() {
        return active;
    }


    public boolean isAccountNonLocked() {
        return active;
    }


    public boolean isCredentialsNonExpired() {
        return active;
    }


    public boolean isEnabled() {
        return active;
    }

}
