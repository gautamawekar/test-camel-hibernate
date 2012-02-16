package test.gawekar.camel;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.spi.ApplicationContextRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import test.gawekar.config.AnnotedConfig;

public class TestCamel {
	public static void main(String args[]) throws Exception{
		DefaultCamelContext ctx = new DefaultCamelContext();
		ctx.setRegistry(new ApplicationContextRegistry(new AnnotationConfigApplicationContext(AnnotedConfig.class)));
		System.out.println(">>>>> " + ctx.getName());
		ctx.start();
		Thread.sleep(20000);
		ctx.stop();
	}
}
