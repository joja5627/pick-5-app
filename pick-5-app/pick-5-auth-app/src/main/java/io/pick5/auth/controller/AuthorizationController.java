
package io.pick5.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationController {

   
//    private final MessageServiceImpl messageService;
    
//    @GetMapping("/")
//    public Flux<FormattedMessage> index() {
//    	
//        return messageService.getDefaultMessage();
//    }
	
//    @GetMapping("/register")
//    public Flux<String> register() {
//    	
//    	//PUT to User Service
//    	
//    	return Flux.just("register");
//        //return messageService.getDefaultMessage();
//    }

//    @GetMapping("/login")
//    public Flux<String> login() {
//    	return Flux.just("login");
//    }

//    @GetMapping("/api/private")
////    @PreAuthorize("hasRole('USER')")
//    public Flux<String> privateMessage() {
//    	return Flux.just("register");
//    }

   
//    @GetMapping("/api/admin")
////    @PreAuthorize("hasRole('ADMIN')")
//    public Flux<String> privateMessageAdmin() {
//    	return Flux.just("register");
//    }

   
//    @GetMapping("/api/guest")
////    @PreAuthorize("hasRole('GUEST')")
//    public Flux<String> privateMessageGuest() {
//    	return Flux.just("register");
//    }
}
