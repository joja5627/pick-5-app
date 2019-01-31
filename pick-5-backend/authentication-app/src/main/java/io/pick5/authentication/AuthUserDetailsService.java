package io.pick5.authentication;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthUserDetailsService implements UserDetailsService {

    private UserServiceClient userServiceClient;

    public AuthUserDetailsService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceClient.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("username not found:" + username);
        }

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            //.authorities(AuthorityUtils.createAuthorityList(user.getRoles().toArray(new String[0])))
            .roles(user.getRoles().toArray(new String[0]))
            .accountLocked(!user.isActive())
            .accountExpired(!user.isActive())
            .disabled(!user.isActive())
            .credentialsExpired(!user.isActive())
            .build();
    }

}
