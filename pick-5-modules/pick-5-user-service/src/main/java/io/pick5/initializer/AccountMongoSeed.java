//package io.pick5.initializer;
//
//import java.util.List;
//import java.util.Objects;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import io.pick5.user.domain.User;
//import io.pick5.user.repo.UserRepository;
//import reactor.core.publisher.Mono;
//
//public class AccountMongoSeed {
//	private static final Logger logger = LoggerFactory.getLogger(AccountMongoSeed.class);
//
//
//    private final UserRepository userRepo;
//    private final PasswordEncoder passwordEncoder;
//    
//    public AccountMongoSeed(UserRepository users, PasswordEncoder passwordEncoder) {
//        this.userRepo = users;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @EventListener(value = ContextRefreshedEvent.class)
//    public void init() {
//    	Mono<User> defAdmin = userRepo.findByUsername("admin");
//    	
//    	if(Objects.isNull(defAdmin)) {
//    		User defaultAdmin = 
//        			User.builder()
//        					.username("admin")
//        						.password(passwordEncoder.encode("admin"))
//        							.roles(List.of("ROLE_USER", "ROLE_ADMIN")).build();
//        	
//        	userRepo.save(defaultAdmin);
//    	}
//    
//    	
//    }
//
////    private void initUsers() {
////    	logger.info("start users initialization  ...");
////        this.users
////            .deleteAll()
////            .thenMany(
////                Flux
////                    .just("user", "admin")
////                    .flatMap(
////                        username -> {
////                            List<String> roles = "user".equals(username)
////                            ? Arrays.asList("ROLE_USER")
////                            : Arrays.asList("ROLE_USER", "ROLE_ADMIN");
////
////                            User user = User.builder()
////                                    .roles(roles)
////                                    .username(username)
////                                    .password(passwordEncoder.encode("password"))
////                                    .build();
////                            return this.users.save(user);
////                        }
////                    )
////            )
////            .log()
////            .subscribe(
////                null,
////                null,
////                () -> log.info("done users initialization...")
////            );
////    }
//
//}
