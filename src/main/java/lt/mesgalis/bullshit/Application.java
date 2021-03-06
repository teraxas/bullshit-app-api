package lt.mesgalis.bullshit;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
@Configuration
@EnableSpringHttpSession
@EnableJpaRepositories("lt.mesgalis.bullshit.data")
@EnableSwagger2
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

	private Config appConfig;

	public Application(Config config) {
		this.appConfig = config;
	}

	public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		printInfo();
    }

    @Bean
    public SessionRepository getSessionRepository() {
        return new MapSessionRepository(Maps.newHashMap());
    }

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		log.info("Allowing cross-origin for: " + appConfig.getAllowedOrigin());
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(appConfig.getAllowedOriginSplit())
						.allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true)
//						.exposedHeaders("*")
				;
			}
		};
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("lt.mesgalis.bullshit.controller"))
				.paths(any())
				.build();
	}

    private static void printInfo() {
		String databaseUrl = System.getenv("JDBC_DATABASE_URL");
		log.info("JDBC_DATABASE_URL: " + databaseUrl);
		log.info(databaseUrl == null ? "Running on in-memory DB" : "Running on PostgreSQL DB");
    }



}
