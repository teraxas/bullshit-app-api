package lt.mesgalis.bullshit.servlet3;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lt.mesgalis.bullshit.config.SpringRootConfig;
import lt.mesgalis.bullshit.config.SpringWebConfig;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	public WebInit() {
		System.out.println("*** WEB INIT ***");
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
}
