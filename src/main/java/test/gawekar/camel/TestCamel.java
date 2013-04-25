package test.gawekar.camel;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.spi.ApplicationContextRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import test.gawekar.config.AnnotedCamelConfig;

public class TestCamel {
	public static void main(String args[]) throws Exception{
		DefaultCamelContext ctx = new DefaultCamelContext();
		ctx.setRegistry(new ApplicationContextRegistry(new AnnotationConfigApplicationContext(AnnotedCamelConfig.class)));
		ctx.start();
		Thread.sleep(5000);
		ctx.stop();
	}
}
