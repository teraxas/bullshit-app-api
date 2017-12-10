package lt.mesgalis.bullshit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by terax on 2017-08-07.
 */
@Component
@PropertySource("classpath:application-dev.properties")
@ConfigurationProperties(prefix = "bs")
public class Config {

	private String allowedOrigin;

	public String[] getAllowedOriginSplit() {
		return allowedOrigin.split("|");
	}

	public String getAllowedOrigin() {
		return allowedOrigin;
	}

	public void setAllowedOrigin(String allowedOrigin) {
		this.allowedOrigin = allowedOrigin;
	}
}
