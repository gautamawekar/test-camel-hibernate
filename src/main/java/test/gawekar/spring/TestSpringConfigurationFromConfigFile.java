package test.gawekar.spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import test.gawekar.beans.HelloBean;


public class TestSpringConfigurationFromConfigFile {
	public static void main(String args[]){
		XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("my-spring-config.xml")); 
		beanFactory.getBean(HelloBean.class).sayHelloWorldFromAnnotedWorld();
		beanFactory.getBean(HelloBean.class).sayHelloWorldFromAnnotedWorld();
		beanFactory.getBean(HelloBean.class).sayHelloWorldFromAnnotedWorld();
	}
}
