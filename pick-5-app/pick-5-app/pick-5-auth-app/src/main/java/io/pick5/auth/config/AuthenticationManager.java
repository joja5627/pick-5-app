package io.pick5.auth.config;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

//	@Autowired
//	private JWTUtil jwtUtil;
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		//roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList()
//		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//		"username",
//		null,
//		)
//	);
	return Mono.empty();
//		return Mono.empty();
//		String authToken = authentication.getCredentials().toString();
//		
//		String username;
//		try {
//			username = jwtUtil.getUsernameFromToken(authToken);
//		} catch (Exception e) {
//			username = null;
//		}
//		if (username != null && jwtUtil.validateToken(authToken)) {
//			Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
//			List<String> rolesMap = claims.get("role", List.class);
//			List<Role> roles = new ArrayList<>();
//			for (String rolemap : rolesMap) {
//				roles.add(Role.valueOf(rolemap));
//			}
//			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//				username,
//				null,
//				roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList())
//			);
//			return Mono.just(auth);
//		} else {
//			return Mono.empty();
//		}
	}
}