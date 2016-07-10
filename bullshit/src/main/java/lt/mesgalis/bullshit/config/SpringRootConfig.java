package lt.mesgalis.bullshit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "lt.mesgalis.bullshit.service" })
public class SpringRootConfig {
}