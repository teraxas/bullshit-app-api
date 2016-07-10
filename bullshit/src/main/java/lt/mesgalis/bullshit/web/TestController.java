package lt.mesgalis.bullshit.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lt.mesgalis.bullshit.service.TestService;

@Controller
public class TestController {

//	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	@Autowired private TestService testService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

//		logger.debug("index() is executed!");

		model.put("title", testService.getTitle(""));
		model.put("msg", testService.getDesc());

		return "index";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

//		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		model.addObject("title", testService.getTitle(name));
		model.addObject("msg", testService.getDesc());

		return model;

	}

}
