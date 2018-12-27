package io.pick5.test.tags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.pick5.auth.AuthenticationApplication;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("IntegrationTest")
@SpringBootTest(classes = AuthenticationApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public @interface IntegrationTest {
}
