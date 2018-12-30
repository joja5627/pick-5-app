package io.pick5.token.controller;

public class JsonWebTokenController {
//	@ExceptionHandler
//	  public CustomResponse handleMissingParams(ServerWebInputException ex) {
//	    return new CustomResponse(null, null, "Please supply a username and email address!");
//	  }

	  /**
	   * CRUD Operations.
	   */
	
//    private static final String BEARER = "Bearer ";
//    private static final Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
//    private static final Function<String,Mono<String>> isolateBearerValue = authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length()));

//    private  JWTCustomVerifier jwtVerifier = new JWTCustomVerifier();
    /**
     * Apply this function to the current WebExchange, an Authentication object
     * is returned when completed.
     *
     * @param serverWebExchange
     * @return  AuthorizationHeaderPayload.java
     */

//	  @PostMapping(Constants.API_FLUX_USER_ONE)
//	  public Mono<CustomResponse> getOneUser(@RequestBody AuthenticatedUuid tokenAuth) {
//	    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth).block();
//	    MongoUser user = new MongoUser(tokenAuth.getUsername(), null, null, null, null);
//	    try {
//	      if (auth) user = userReactiveWebService.findOneUserById(tokenAuth.getId()).block();
//	    } catch (Exception ex) {
//	      log.error("Exception: " + ex);
//	      return Mono.just(new CustomResponse(user, "Failed!"));
//	    }
//	    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
//	  }

//	  @PostMapping(Constants.API_FLUX_USER_NEW)
//	  public Mono<CustomResponse> saveOneUser(@RequestBody AuthenticatedUpdate tokenAuth) {
//	    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth.getUsername(), tokenAuth.getToken()).block();
//	    log.info(tokenAuth.getNewUsername() + tokenAuth.getNewName() + tokenAuth.getNewPhone() + tokenAuth.getNewEmail());
//	    MongoUser user = new MongoUser(tokenAuth.getNewUsername(), tokenAuth.getNewName(), tokenAuth.getNewPhone(), new MongoEmail(tokenAuth.getNewEmail()), null);
//	    try {
//	      log.info(user.toString());
//	      if (auth) userReactiveWebService
//	            .saveUser(tokenAuth.getNewUsername(),
//	                tokenAuth.getNewName(),
//	                tokenAuth.getNewPhone(),
//	                tokenAuth.getNewEmail());
//	    } catch (Exception ex) {
//	      log.error("Exception: " + ex);
//	      return Mono.just(new CustomResponse(user,"Failed!"));
//	    }
//	    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
//	  }

//	  @DeleteMapping(Constants.API_FLUX_USER_ONE)
//	  public Mono<CustomResponse> deleteOneUser(@RequestBody AuthenticatedUuid tokenAuth) {
//	    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth).block();
//	    MongoUser user = new MongoUser(tokenAuth.getId(), null, null, null, null);
//	    try {
//	      if (auth) userReactiveWebService.deleteUser(tokenAuth.getId());
//	    } catch (Exception ex) {
//	      log.error("Exception: " + ex);
//	      return Mono.just(new CustomResponse(user,"Failed!"));
//	    }
//	    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
//	  }

//	  @PutMapping(Constants.API_FLUX_USER_ONE)
//	  public Mono<CustomResponse> updateOneUser(@RequestBody AuthenticatedUpdate tokenAuth) {
//	    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth.getUsername(), tokenAuth.getToken()).block();
//	    log.info(tokenAuth.getNewUsername() + tokenAuth.getNewName() + tokenAuth.getNewPhone() + tokenAuth.getNewEmail());
//	    MongoUser user = new MongoUser(tokenAuth.getNewUsername(), tokenAuth.getNewName(), tokenAuth.getNewPhone(), new MongoEmail(tokenAuth.getNewEmail()), null);
//	    try {
//	      if (auth) userReactiveWebService
//	            .updateUser(tokenAuth.getNewUsername(),
//	                tokenAuth.getNewName(),
//	                tokenAuth.getNewPhone(),
//	                tokenAuth.getNewEmail());
//	    } catch (Exception ex) {
//	      log.error("Exception: " + ex);
//	      return Mono.just(new CustomResponse(user, "Failed!"));
//	    }
//	    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
//	  }

//	  @PostMapping(Constants.API_FLUX_USER_ALL)
//	  public Mono<CustomResponse> getAllUsers(@RequestBody TokenAuth tokenAuth) {
//	    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth).block();
//	    List<MongoUser> userList = new ArrayList<>();
//	    try {
//	      if (auth) {
//	        userList = userReactiveWebService.findAllUsers().block();
//	      }
//	    } catch (Exception ex) {
//	      log.error("Exception: " + ex);
//	      return Mono.just(new CustomResponse(userList, "Failed!"));
//	    }
//	    return Mono.just(new CustomResponse(userList, (auth) ? "Success!" : "Failed!"));
//	  }
}
