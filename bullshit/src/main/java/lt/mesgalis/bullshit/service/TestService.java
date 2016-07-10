package lt.mesgalis.bullshit.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TestService {

//	private static final Logger logger = LoggerFactory.getLogger(TestService.class);

	public String getDesc() {

//		logger.debug("getDesc() is executed!");

		return "Gradle + Spring MVC Hello World Example";

	}

	public String getTitle(String name) {

//		logger.debug("getTitle() is executed! $name : {}", name);

		if(StringUtils.isEmpty(name)){
			return "Hello World";
		}else{
			return "Hello " + name;
		}
		
	}

}
