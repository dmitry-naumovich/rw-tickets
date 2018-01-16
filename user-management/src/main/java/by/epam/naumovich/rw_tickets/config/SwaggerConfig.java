package by.epam.naumovich.rw_tickets.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration of Swagger 2
 */
@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {

    private static final String API_VERSION = "0.0.1-snapshot";
    private static final String API_DESCRIPTION = "API of user-management module";
    private static final String API_TITLE = "RW tickets user-management REST API";

    @Bean
    public Docket user_management_api() {
        return new VersionedDocket(API_VERSION);
    }

    class VersionedDocket extends Docket {
        public VersionedDocket(String versionInfo) {
            super(DocumentationType.SWAGGER_2);
            super.groupName(versionInfo)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("by.epam.naumovich.rw_tickets.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo(versionInfo));
        }

        private ApiInfo apiInfo(String version) {
            return new ApiInfo(
                    API_TITLE,
                    API_DESCRIPTION,
                    version,
                    null,
                    ApiInfo.DEFAULT_CONTACT,
                    null,
                    null,
                    new ArrayList<>());
        }
    }
}
