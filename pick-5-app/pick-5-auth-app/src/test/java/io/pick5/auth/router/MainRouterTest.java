package io.pick5.auth.router;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.RouterFunction;

import io.pick5.auth.BasicIntegrationTest;
import io.pick5.test.tags.IntegrationTest;


@IntegrationTest
@DisplayName("MainRouter Integration Tests")
class MainRouterTest extends BasicIntegrationTest {

    private static final String STATIC_ROUTE = "/index.html";
   

    @Autowired
    private RouterFunction<?> mainRouterFunction;


    @BeforeEach
    void setup() {
        super.bindToRouterFunction(mainRouterFunction);
    }

    @BeforeAll
    static void setupAll() {
        final MainRouter mainRouter = new MainRouter();
    }

    @Test
    void staticRouteTest() {
        get(builder -> builder.path(STATIC_ROUTE).build());
    }

    
}
