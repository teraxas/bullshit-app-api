package lt.mesgalis.bullshit;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import lt.mesgalis.bullshit.servlet3.WebInit;

@SpringBootApplication
@ComponentScan({"lt.mesgalis.bullshit.config", "lt.mesgalis.bullshit.servlet3" })
public class Application {
	
	private static ApplicationContext ctx;

	public static void main(String[] args) throws Exception {
		ctx = SpringApplication.run(new Class[] {Application.class, WebInit.class}, args);

        logBeans();
	}

	private static void logBeans() {
		System.out.println("Spring Boot beans:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
	}

	public static ApplicationContext getContext() {
		return ctx;
	}

}
