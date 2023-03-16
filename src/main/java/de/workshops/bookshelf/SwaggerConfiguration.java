package de.workshops.bookshelf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfiguration {

    @Autowired
    BookshelfProperties bookshelfProperties;

    @Bean
    @Profile("dev")
    public OpenAPI api() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title(bookshelfProperties.getTitle())
                                .version(bookshelfProperties.getVersion())
                                .license(new License()
                                        .name("MIT License")
                                        .url("https://opensource.org/licenses/MIT")
                                )
                );
    }
}
