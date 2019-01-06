package io.pick5.tags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.pick5.domain.DomainModuleConfig;
import io.pick5.exceptions.ExceptionsModuleConfig;
import io.pick5.handlers.HandlersModuleConfig;
import io.pick5.registration.RegistrationApplication;

@Tag("IntegrationTest")
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Import({HandlersModuleConfig.class,DomainModuleConfig.class,ExceptionsModuleConfig.class})
@SpringBootTest(classes = {RegistrationApplication.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public @interface IntegrationTest {
}
