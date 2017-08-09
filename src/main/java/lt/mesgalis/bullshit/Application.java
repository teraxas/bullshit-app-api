package lt.mesgalis.bullshit;

import org.apache.catalina.filters.CorsFilter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableSpringHttpSession
@EnableJpaRepositories("lt.mesgalis.bullshit.data")
public class Application {

    private static final Logger log = LogManager.getLogger(Application.class);

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
        return new MapSessionRepository();
    }

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(appConfig.getAllowedOrigin())
						.allowedMethods("PUT", "DELETE", "GET", "POST");
			}
		};
	}

//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin(appConfig.getAllowedOrigin());
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//		bean.setOrder(0);
//		return bean;
//	}

    public static void printInfo() {
		String databaseUrl = System.getenv("JDBC_DATABASE_URL");
		log.info("JDBC_DATABASE_URL: " + databaseUrl);
		log.info(databaseUrl == null ? "Running on in-memory DB" : "Running on PostgreSQL DB");
    }



}
