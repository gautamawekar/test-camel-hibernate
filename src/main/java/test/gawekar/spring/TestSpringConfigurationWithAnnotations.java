package test.gawekar.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import test.gawekar.beans.HelloBean;
import test.gawekar.beans.HelloBean2;
import test.gawekar.config.AnnotedConfig;

public class TestSpringConfigurationWithAnnotations {
	public static void main(String args[]){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotedConfig.class);
		context.getBean(HelloBean.class).sayHelloWorldFromAnnotedWorld();
		context.getBean(HelloBean.class).sayHelloWorldFromAnnotedWorld();
		context.getBean(HelloBean2.class).sayHelloWorldFromAnnotedWorld();
		System.out.println(context.getBean("helloBean123"));
		System.out.println(context.getBean("helloBean1"));
	}
}
