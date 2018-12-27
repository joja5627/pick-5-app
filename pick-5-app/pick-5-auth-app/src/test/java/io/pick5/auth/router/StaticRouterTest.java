package io.pick5.auth.router;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import io.pick5.auth.BasicIntegrationTest;
import io.pick5.test.tags.IntegrationTest;



@IntegrationTest
@DisplayName(" StaticRouter Integration Tests")
class StaticRouterTest extends BasicIntegrationTest {

    private static final String STATIC_PATH = "/index.html";
    private static final String DEFAULT_TITLE = "Swagger UI";
    private static final String TITLE_TAG = "title";

    @BeforeEach
    void setup() {
        super.bindToRouterFunction(StaticRouter.doRoute());
    }

    @BeforeAll
    static void setupAll() {
        final StaticRouter staticRouter = new StaticRouter();
    }

//    @Test
//    void staticContentTest() {
//        String result = get(builder -> builder.path(STATIC_PATH).build());
//        assertThat(result, not(isEmptyOrNullString()));
//        verifyTitleIs(result, DEFAULT_TITLE);
//    }

//    private void verifyTitleIs(final String html, final String title) {
//        Document doc = Jsoup.parse(html);
//        Element element = doc.head().getElementsByTag(TITLE_TAG).get(0);
//        String text = element.text();
//        assertThat(text, is(title));
//    }
}
