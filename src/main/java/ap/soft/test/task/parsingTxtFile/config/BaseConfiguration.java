package ap.soft.test.task.parsingTxtFile.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
import javax.servlet.MultipartConfigElement;

@Configuration
@ComponentScan
@PropertySource(value = "classpath:application.properties")
@EnableAutoConfiguration
public class BaseConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(1));
        return factory.createMultipartConfig();
    }
}
