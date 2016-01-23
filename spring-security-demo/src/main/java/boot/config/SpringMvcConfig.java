package boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@EnableWebMvc
public class SpringMvcConfig {
	public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
		// tag::config[]
		@Override
		protected Class<?>[] getRootConfigClasses() {
			return new Class[] { SpringSecurityConfig.class, SpringSessionConfig.class };
		}
		// end::config[]

		@Override
		protected Class<?>[] getServletConfigClasses() {
			return new Class[] { SpringMvcConfig.class };
		}

		@Override
		protected String[] getServletMappings() {
			return new String[] { "/" };
		}
	}
}
