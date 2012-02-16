package test.gawekar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import test.gawekar.beans.HelloBean;
import test.gawekar.beans.ToBeCalledBean;

@Configuration
@ImportResource(value={"classpath:my-spring-config1.xml","classpath:my-routes-context.xml"})
public class AnnotedConfig {
	
	public @Bean HelloBean helloBean123(){
		return new HelloBean();
	}
	
	public @Bean ToBeCalledBean toBeCalledBean(){
		return new ToBeCalledBean();
	}
}
