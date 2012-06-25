package test.gawekar.routes;

import org.apache.camel.builder.RouteBuilder;

public class PersistPersonRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:persistPerson").
		to("bean:persistPerson").
		to("jpa:?persistenceUnit=test-gawekar-mysql-local");
	}
}
