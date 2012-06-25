package test.gawekar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={"classpath:my-spring-config1.xml","classpath:my-spring-config.xml"})
public class AnnotedSpringConfig {
	
//	public @Bean HelloBean helloBean123(){
//		return new HelloBean();
//	}
//	
//	public @Bean ToBeCalledBean toBeCalledBean(){
//		return new ToBeCalledBean();
//	}
}
