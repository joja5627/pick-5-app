//package io.pick5.authentication;
//
//
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit.WireMockRule;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Slf4j
//public class UserServiceClientWireMockTest {
//
//    @Value("${services.user-service-url:http://localhost:8001}")
//    private String userServiceUrl;
//
//    @Autowired
//    private UserServiceClient client;
//
//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule(options().port(8001));
//
//    @Before
//    public void setup() {
//        WireMock.reset();
//    }
//
//    @Test
//    public void testFindbyUsername() {
//
//        stubFor(
//            get("/users/user")
//                //.withHeader("Accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
//                .willReturn(
//                    okJson("{\"username\":\"user\",\"password\":\"password\",\"email\":\"user@example.com\"}")
//                )
//        );
//
//        User user = this.client.findByUsername("user");
//        assertNotNull(user);
//        assertEquals("user", user.getUsername());
//
//        verify(1, getRequestedFor(urlMatching( "/users/user")));
//    }
//
//    @Test
//    public void testFindbyUsername_notFound() {
//        stubFor(
//            get( "/users/user1")
//                //.withHeader("Accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
//                .willReturn(
//                    aResponse()
//                        .withStatus(HttpStatus.SC_NOT_FOUND)
//                )
//        );
//
//        User user = this.client.findByUsername("user1");
//        assertNull(user);
//
//        verify(1, getRequestedFor(urlMatching( "/users/user1")));
//    }
//}
