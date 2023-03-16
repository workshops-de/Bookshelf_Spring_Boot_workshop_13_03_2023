package de.workshops.bookshelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=18080"})
public class EnvironmentTest {

    @Value("${server.port}")
    private int port;

    @Test
    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'prod'}", loadContext = true)
    void testProdPort() {
        assertThat(port).isEqualTo(18080);
    }
}
