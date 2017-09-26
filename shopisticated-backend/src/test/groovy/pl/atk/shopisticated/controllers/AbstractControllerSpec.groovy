package pl.atk.shopisticated.controllers

import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import pl.atk.shopisticated.ShopisticatedApplication
import spock.lang.Specification

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration

@ContextConfiguration(classes = ShopisticatedApplication, initializers = ConfigFileApplicationContextInitializer)
@WebAppConfiguration
abstract class AbstractControllerSpec extends Specification {

    public static final String OUTPUT_DIR = "src/asciidoc/rest"

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(OUTPUT_DIR)

    protected MockMvc mockMvc

    @Autowired
    private WebApplicationContext context

    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(
                documentationConfiguration(restDocumentation)
                        .snippets()
                        .withEncoding("UTF-8")
                        .and()
                        .uris()
                        .withHost("example.com")
                        .withPort(80)

        ).build();
    }

}

