package test.gawekar.routes;

import org.apache.camel.builder.RouteBuilder;

public class TimerRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		System.out.println("inside timer route");
		System.out.println(">>>>><<<<<<<<< " + this.getContext().getName());
		//from("timer://foo?fixedRate=true&period=5000").to("bean:toBeCalledBean?method=callme");
		//from("timer://abc?fixedRate=true&period=10000").to("abcConsumer");
	}

}
