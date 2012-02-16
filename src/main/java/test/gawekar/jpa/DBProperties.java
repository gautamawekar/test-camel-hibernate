package test.gawekar.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class DBProperties {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map createUnitTestDBConnectionProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
		properties.put("hibernate.connection.url", "jdbc:hsqldb:mem:exporter");
		properties.put("hibernate.connection.username", "sa");
		properties.put("hibernate.connection.password", "");
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		Map pp = new HashMap();
		for(Entry<Object, Object> entry: properties.entrySet()) {
			pp.put(entry.getKey(), entry.getValue());
		}
		
		return pp;
	}
}
