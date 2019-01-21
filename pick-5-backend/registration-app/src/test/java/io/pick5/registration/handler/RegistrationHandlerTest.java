package io.pick5.registration.handler;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.pick5.registration.RegistrationApplication;
import io.pick5.registration.service.EmailServiceProxyRetryable;
import io.pick5.registration.service.UserServiceProxyRetryablImpl;


@Tag("IntegrationTest")
@SpringBootTest(classes = RegistrationApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RegistrationHandlerTest {
	
	
	@Mock
	private UserServiceProxyRetryablImpl userService;
	
	@Mock
	private EmailServiceProxyRetryable emailService;
	
	@Autowired
	private RegistrationHandler registrationHandler;
	
	

}
