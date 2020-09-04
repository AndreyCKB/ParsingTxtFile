package ap.soft.test.task.parsingTxtFile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class ParsingTxtFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParsingTxtFileApplication.class, args);
	}

}
