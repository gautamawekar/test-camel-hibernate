package test.gawekar.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import test.gawekar.beans.HelloBean;
import test.gawekar.beans.HelloBean2;
import test.gawekar.config.AnnotedSpringConfig;

public class TestSpringConfigurationWithAnnotations {
	public static void main(String args[]){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotedSpringConfig.class);
		context.getBean(HelloBean.class).sayHelloWorldFromAnnotedWorld();
		context.getBean(HelloBean.class).sayHelloWorldFromAnnotedWorld();
		context.getBean(HelloBean2.class).sayHelloWorldFromAnnotedWorld();
	}
}
