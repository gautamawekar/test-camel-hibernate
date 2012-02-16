package test.gawekar.beans;

import org.apache.camel.Consume;

public class ToBeCalledBean {
	
	public ToBeCalledBean(){
		
	}
	
	public void callme(){
		System.out.println("I am called---yepppppppp!!!!!!!!!!!");
	}
	
	@Consume(uri="timer://abc?fixedRate=true&period=10000")
	public void testConsumeAnnotation(String message){
		System.out.println("+++++++++++++++++test consume with annotations - called - yeppp!!!! : " + message);
	}
}
