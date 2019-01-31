package io.pick5.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pick5.domain.User;
import io.pick5.user.repo.UserRepository;
import reactor.core.publisher.Mono;


@Service
public class UserService {

   @Autowired
    private UserRepository userRepository;

    public Mono<User> createUser(User user) throws Exception {
        if (this.userRepository.findByUsername(user.getUsername()).isPresent()) {
        	throw new Exception("Username was taken");
            //throw new UsernameWasTakenException("username was taken");
        }

        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
        	throw new Exception("Email was taken");
            //throw new EmailWasTakenException("email was taken");
        }

        return this.userRepository.save(user);
    }

    public Mono<User> updateUser(String username, User form) throws Exception {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> {
                return new Exception("User not found");
            }
        );
        
            
        user.setEmail(form.getEmail());
        
        return this.userRepository.save(user);
    }

    public Mono<User> lock(String username) throws Exception {
         User _user = this.userRepository.findByUsername(username).orElseThrow(
            () -> {
                return new Exception("User not found");
            }
        );
        
            
        _user.setActive(false);
        
        return this.userRepository.save(_user);
    }

    public Mono<User> unlock(String username) throws Exception {
         User user = this.userRepository.findByUsername(username).orElseThrow(
            () -> {
            	return new Exception("User not found");
            }
        );
            
        user.setActive(true);
        
        return this.userRepository.save(user);
    }
}
