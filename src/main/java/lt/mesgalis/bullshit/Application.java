package lt.mesgalis.bullshit;

import static javaslang.API.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import java.util.Arrays;

@SpringBootApplication
@EnableSpringHttpSession
@EnableJpaRepositories("lt.mesgalis.bullshit.data")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        printLoadedBeans(ctx);
    }

    @Bean
    public SessionRepository getSessionRepository() {
        return new MapSessionRepository();
    }

    public static void printLoadedBeans(ConfigurableApplicationContext ctx) {
        System.out.println("Beans loaded:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println("   " + beanName);
        }
    }

}
