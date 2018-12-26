package io.pick5.user.router;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import io.pick5.handlers.ErrorHandler;
import io.pick5.tags.IntegrationTest;
import io.pick5.test.utils.BasicIntegrationTest;
import io.pick5.user.handler.UserApiHandler;


@IntegrationTest
@DisplayName("UserHandlerTest Integration Tests")
@ContextConfiguration(classes = UserRouterTestConfig.class)
public class UserRouterTest extends BasicIntegrationTest {
	
	  private static final String WRONG_PATH = "/api/wrong";
	  private static final String GOOGLE_ADDRESS = "1600 Amphitheatre Parkway, Mountain View, CA";
	  private static final double GOOGLE_LAT = 37.4224082;
	  private static final double GOOGLE_LNG = -122.0856086;
	  private static final String NOT_FOUND = "not found";
	  private static final String BIG_ERROR = "big error";
	  private static final String SUNRISE_TIME = "12:55:17 PM";
	  private static final String SUNSET_TIME = "3:14:28 AM";
	
	  @Autowired
	  private UserApiHandler userHandlerApi;
	  @Autowired
	  private ErrorHandler errorHandler;
//	  
//	  
//	  private void verifyServerResponse(final ServerResponse serverResponse) {
//
//	        assertThat(serverResponse.statusCode(), is(HttpStatus.OK));
//
//	        final UserEntity user = HandlersHelper
//	        			.extractEntity(serverResponse, UserEntity.class);
//
//	    }
//	  
//	  
//	  
//	
//	  
//	  @BeforeEach
//	  void setup() {
//		 
//		  super.bindToRouterFunction(UserRouter.doRoute(userHandlerApi, errorHandler));
//	  }
//
//	  @BeforeAll
//	  static void setupAll() {
//	     final UserRouter router = new UserRouter();
//	  }
//	
//	  
//	  @Test
//	  public void whenExceptionThrown_thenAssertionSucceeds() {
//	      String test = null;
//	      assertThrows(NullPointerException.class, () -> {
//	          test.length();
//	      });
//	  }
	  
//	  @Test
//	  void getWrongPath() {
//			get(builder -> builder.path(WRONG_PATH).build());
//	    }

	  

	  
	  
	  
	 //builder -> builder.path(WRONG_PATH).build()
	 
	
	

}
