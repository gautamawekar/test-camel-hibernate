package test.gawekar.routes;

import org.apache.camel.builder.RouteBuilder;

public class HelloBeanRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		System.out.println("call hello bean route initialized........");
		from("direct:callHelloBean").to("bean:helloBean");
		
	}
	
}
