package boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	 @Autowired
	  private ThymeleafProperties properties;

	  @Value("${spring.thymeleaf.prefix:}")
	  private String templatesRoot;

	  @Bean
	  public ITemplateResolver defaultTemplateResolver() {
	    TemplateResolver resolver = new ClassLoaderTemplateResolver();
	    resolver.setSuffix(properties.getSuffix());
	    resolver.setPrefix(templatesRoot);
	    resolver.setTemplateMode(properties.getMode());
	    resolver.setCacheable(properties.isCache());
	    return resolver;
	  }
}
